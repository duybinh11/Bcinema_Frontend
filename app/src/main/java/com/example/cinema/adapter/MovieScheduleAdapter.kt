package com.example.cinema.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinema.databinding.LayoutMovieScheduleBinding
import com.example.cinema.model.MovieModel

class MovieScheduleAdapter(val list : List<MovieModel>) : RecyclerView.Adapter<MovieScheduleAdapter.MovieSchudeleHolder>(){
    private lateinit var binding : LayoutMovieScheduleBinding
    class MovieSchudeleHolder(val binding: LayoutMovieScheduleBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie : MovieModel){
            Glide.with(binding.root.context).load(movie.poster).into(binding.avt)
            binding.gridViewTime.adapter = ScheduleTimeAdapter(binding.root.context,movie.listShowTime)
            binding.gridViewTime.layoutManager = GridLayoutManager(binding.root.context,3,GridLayoutManager.HORIZONTAL,false)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSchudeleHolder {
        binding = LayoutMovieScheduleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieSchudeleHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieSchudeleHolder, position: Int) {
        holder.bind(list[position])
    }
}