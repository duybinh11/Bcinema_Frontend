package com.example.cinema.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinema.adapter.SeatAdapter
import com.example.cinema.databinding.ActivitySeatBinding
import com.example.cinema.repository.SeatRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.SeatViewModel
import com.example.demo1.util.Resource

class SeatActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySeatBinding
    private lateinit var viewModel : SeatViewModel
    private  var idShowTime = 0
    private lateinit var adapter: SeatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        adapter = SeatAdapter(this,viewModel)
        getShowTime()
        binding = ActivitySeatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gridView.adapter = adapter
        viewModel.getSeatBooked(idShowTime)
        liveCost()
        clickBuySeat()
        liveSeatBooked()

    }

    override fun onDestroy() {
        viewModel.resetSeat()
        super.onDestroy()
    }

    private fun liveSeatBooked() {
        viewModel.stateSeatBooked.observe(this, Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    adapter.setListSeatBooked(it.data)
                }
                is Resource.Error ->{
                    notLoading()
                }
                else ->{}
            }
        })
    }

    private fun notLoading() {
        binding.apply {
            loading.visibility = View.INVISIBLE
            body.visibility =View.VISIBLE
        }
    }

    private fun isLoading() {
        binding.apply {
            loading.visibility = View.VISIBLE
            body.visibility =View.INVISIBLE
        }
    }

    private fun getShowTime() {
        idShowTime = intent.getIntExtra("id_show_time",0)
    }


    private fun liveCost() {
        viewModel.stateCost.observe(this, Observer {
            binding.money.text = "${it.size * 100000} VND"
        })
    }
    private fun initViewModel(){
        val myViewModelFactory = MyViewModelFactory(SeatRepository())
        viewModel = ViewModelProvider(this,myViewModelFactory)[SeatViewModel::class.java]
    }

    private fun clickBuySeat(){
        binding.btnBuy.setOnClickListener {
            if(viewModel.stateCost.value?.size!! > 0){
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra("list_seat",viewModel.stateCost.value?.toIntArray())
                intent.putExtra("id_show_time",idShowTime)
                finish()
                startActivity(intent)
            }else{
                Toast.makeText(this,"Vui lòng chọn ghế",Toast.LENGTH_LONG).show()
            }

        }
    }
}
