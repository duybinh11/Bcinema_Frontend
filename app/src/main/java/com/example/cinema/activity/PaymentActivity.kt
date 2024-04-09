package com.example.cinema.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinema.databinding.ActivityPaymentBinding
import com.example.cinema.pojo.PaymentRequest
import com.example.cinema.repository.PaymentRepository
import com.example.cinema.repository.SeatRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.PaymentViewModel
import com.example.cinema.viewmodel.SeatViewModel
import com.example.demo1.util.Resource

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPaymentBinding
    private lateinit var listSeat: List<Int>
    private lateinit var viewModel: PaymentViewModel
    private var idShowTime = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getDataIntent()
        showSeat()
        showMoney()
        clickPayment()
        liveStateBuyTick()
    }

    private fun liveStateBuyTick() {
        viewModel.stateBuyTicket.observe(this, Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    val intent = Intent(this, MyTickerActivity::class.java)
                    intent.putExtra("id_ticket",it.data)
                    Toast.makeText(this,"id_ticket ${it.data}",Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
                is Resource.Error ->{
                    notLoading()
                    Toast.makeText(this,"${it.message}",Toast.LENGTH_SHORT).show()
                }
                else ->{}
            }
        })
    }

    private fun notLoading() {
        binding.loading.visibility = View.INVISIBLE
        binding.body.visibility = View.VISIBLE
    }

    private fun isLoading() {
        binding.loading.visibility = View.VISIBLE
        binding.body.visibility = View.INVISIBLE
    }

    private fun initViewModel() {
        val myViewModelFactory = MyViewModelFactory(PaymentRepository(this))
        viewModel = ViewModelProvider(this,myViewModelFactory)[PaymentViewModel::class.java]
    }

    private fun clickPayment() {
        binding.btnPay.setOnClickListener {
            val request = PaymentRequest(idShowTime,listSeat.size*100000,listSeat)
            Log.d("apiT","request = $request")
            viewModel.buyTick(request)

        }
    }

    private fun showMoney() {
        binding.money.text = "${listSeat.size *100000} VND"
    }

    private fun showSeat() {
        val seatStrings = mutableListOf<String>()
        val sortedList = listSeat.sortedBy { it }
        sortedList.forEach {p0->
            val seat = when (p0 / 10) {
                0 -> "A$p0"
                1 -> "B${p0 - 10}"
                2 -> "C${p0 - 20}"
                3 -> "D${p0 - 30}"
                4 -> "E${p0 - 40}"
                5 -> "F${p0 - 50}"
                6 -> "G${p0 - 60}"
                7 -> "H${p0 - 70}"
                8 -> "J${p0 - 80}"
                9 -> "K${p0 - 90}"
                10 -> "L${p0 - 100}"
                else -> throw IllegalArgumentException("Invalid seat index")
            }
            seatStrings.add(seat)
        }
        binding.seatNumber.text = seatStrings.joinToString(",")
    }

    private fun getDataIntent(){
         listSeat = intent.getIntArrayExtra("list_seat")?.toList() ?: emptyList()
         idShowTime = intent.getIntExtra("id_show_time",0)
        Log.d("apiT","listSeat = $listSeat , id_show_time = $idShowTime")
    }
}