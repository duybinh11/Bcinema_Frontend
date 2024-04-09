package com.example.cinema.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinema.R
import com.example.cinema.activity.DetailMovieActivity
import com.example.cinema.databinding.LayoutComingBinding
import com.example.cinema.model.MovieModel

class MovieComingAdapter(val list: List<MovieModel>) : RecyclerView.Adapter<MovieComingAdapter.ComingSoonHolder>() {
    class ComingSoonHolder(val binding: LayoutComingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: MovieModel){
            binding.apply {
                Glide.with(binding.root.context).load(movie.poster).into(avt)
                name.text = movie.name
                dateStart.text = movie.dateStart
                type.text = movie.movieType
            }
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context,DetailMovieActivity::class.java)
                intent.putExtra("movie",movie)
                binding.root.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComingSoonHolder {

        var binding: LayoutComingBinding = LayoutComingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ComingSoonHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ComingSoonHolder, position: Int) {
        holder.bind(list[position])
    }
}