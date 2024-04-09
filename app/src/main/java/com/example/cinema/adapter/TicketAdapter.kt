package com.example.cinema.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinema.activity.MyTickerActivity
import com.example.cinema.databinding.LayoutTicketBinding
import com.example.cinema.model.MovieModel
import com.example.cinema.model.ShowTimeModel
import com.example.cinema.model.TicketModel
import com.example.cinema.pojo.TicketAllResponse

class TicketAdapter(val list: List<TicketAllResponse>) : RecyclerView.Adapter<TicketAdapter.TicketHolder>(){
    class TicketHolder(val binding : LayoutTicketBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie : MovieModel, ticket : TicketModel,showTime : ShowTimeModel){
            Glide.with(binding.root.context).load(movie.poster).into(binding.avt)
            binding.apply {
                name.text = movie.name
                time.text = movie.time.toString()
                day.text = showTime.dateTime
             }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context,MyTickerActivity::class.java)
                intent.putExtra("id_ticket",ticket.id)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketHolder {
        val binding = LayoutTicketBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TicketHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TicketHolder, position: Int) {
        holder.bind(list[position].movie,list[position].ticket,list[position].showTime)

    }
}