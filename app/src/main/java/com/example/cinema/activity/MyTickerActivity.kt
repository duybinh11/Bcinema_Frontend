package com.example.cinema.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.Glide
import com.example.cinema.R
import com.example.cinema.databinding.ActivityMyTickerBinding
import com.example.cinema.model.MovieModel
import com.example.cinema.model.SeatModel
import com.example.cinema.model.ShowTimeModel
import com.example.cinema.model.TicketModel
import com.example.cinema.repository.MyTicketRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.MyTicketViewModel
import com.example.demo1.util.Resource

class MyTickerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyTickerBinding
    private var idTicket = 0
    private lateinit var viewModel: MyTicketViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyTickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getDataIntent()
        viewModel.getATicket(idTicket)
        liveTicket()
        ClickBack()
    }

    private fun liveTicket() {
        viewModel.stateTicket.observe(this, Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    Log.d("apiT","${it.data}")
                    bindData(it.data.ticket,it.data.movie,it.data.movie.listShowTime[0])
                }

                else->{

                }
            }
        })
    }
    private fun bindData(ticket : TicketModel,movie : MovieModel,show : ShowTimeModel){
        Glide.with(this).load(movie.poster).into(binding.avt)
        binding.apply {
            name.text = movie.name
            time.text = movie.time.toString()
            type.text = movie.movieType
            timeShow.text = show.timeStart
            dateShow.text = show.dateTime
            seat.text = showSeat(ticket.listSeat).joinToString(",")
            money.text = ticket.totalPrice.toString()
        }
    }

    private fun initViewModel() {
        val myViewModelFactory = MyViewModelFactory(MyTicketRepository(this))
        viewModel = ViewModelProvider(this,myViewModelFactory)[MyTicketViewModel::class.java]
    }

    private fun getDataIntent() {
        idTicket = intent.getIntExtra("id_ticket",0)
    }


    private fun notLoading() {
        binding.loading.visibility = View.INVISIBLE
        binding.body.visibility = View.VISIBLE
    }

    private fun isLoading() {
        binding.loading.visibility = View.VISIBLE
        binding.body.visibility = View.INVISIBLE
    }
    private fun showSeat(listSeat : List<SeatModel>) : MutableList<String> {
        val seatStrings = mutableListOf<String>()
        val sortedList = listSeat.sortedBy { it.numberSeat }
        sortedList.forEach {p0->
            val seat = when (p0.numberSeat / 10) {
                0 -> "A$p0"
                1 -> "B${p0.numberSeat - 10}"
                2 -> "C${p0.numberSeat - 20}"
                3 -> "D${p0.numberSeat - 30}"
                4 -> "E${p0.numberSeat - 40}"
                5 -> "F${p0.numberSeat - 50}"
                6 -> "G${p0.numberSeat - 60}"
                7 -> "H${p0.numberSeat - 70}"
                8 -> "J${p0.numberSeat - 80}"
                9 -> "K${p0.numberSeat - 90}"
                10 -> "L${p0.numberSeat - 100}"
                else -> throw IllegalArgumentException("Invalid seat index")
            }
            seatStrings.add(seat)
        }
        return seatStrings
    }
    private fun ClickBack(){
        binding.iconBack.setOnClickListener {
            finish();
        }
    }

}