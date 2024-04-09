package com.example.cinema.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cinema.activity.SeatActivity
import com.example.cinema.databinding.LayoutScheduleTimeBinding
import com.example.cinema.model.ShowTimeModel

class ScheduleTimeAdapter(val context: Context,val list : List<ShowTimeModel>) :  RecyclerView.Adapter<ScheduleTimeAdapter.ScheduleTime>(){
    private lateinit var binding : LayoutScheduleTimeBinding
    class ScheduleTime(val binding: LayoutScheduleTimeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(showTime: ShowTimeModel){
            binding.timeStart.text = showTime.timeStart.substring(0,5)
            binding.timeEnd.text = showTime.timeEnd.substring(0,5)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleTime {
        binding = LayoutScheduleTimeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ScheduleTime(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ScheduleTime, position: Int) {
        holder.bind(list[position])
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context,SeatActivity::class.java)
            intent.putExtra("id_show_time",list[position].id)
            binding.root.context.startActivity(intent)
        }
    }
}