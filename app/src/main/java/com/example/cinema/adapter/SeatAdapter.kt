package com.example.cinema.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.example.cinema.R
import com.example.cinema.databinding.LayoutSeatBinding
import com.example.cinema.model.SeatModel
import com.example.cinema.viewmodel.SeatViewModel

class SeatAdapter(val context: Context,val viewModel: SeatViewModel) : BaseAdapter() {
    private val listSeat = mutableListOf<Int>()
    private lateinit var listSeatBooked : List<SeatModel>

    fun setListSeatBooked(listSeatBooked : List<SeatModel>){
        this.listSeatBooked = listSeatBooked

        notifyDataSetChanged()
    }
    override fun getCount(): Int {
        return 100
    }

    override fun getItem(p0: Int): Any {
        return true
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ResourceAsColor")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = LayoutSeatBinding.inflate(LayoutInflater.from(context))

        // Set initial seat text and background color
        binding.seat.text = when (p0 / 10) {
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
        if(listSeat.contains(p0)){
            binding.root.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.colorbtn));
        }

        if(::listSeatBooked.isInitialized){
            listSeatBooked.forEach {
                if(it.numberSeat == p0){
                    binding.root.backgroundTintList = ColorStateList.valueOf(context.getColor(R.color.colorBooked));
                    binding.root.isEnabled = false
                }
            }
        }else{
            Log.d("apiT","not initializied");
        }

        binding.root.setOnClickListener {
            if(listSeat.contains(p0)){
                viewModel.removeSeat(p0)
                listSeat.remove(p0)
            }else{
                viewModel.addSeat(p0)
                listSeat.add(p0)
            }
            notifyDataSetChanged()
        }

        return binding.root
    }

}