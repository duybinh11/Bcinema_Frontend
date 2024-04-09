package com.example.cinema.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinema.activity.DetailMovieActivity
import com.example.cinema.databinding.LayoutMovieShowBinding
import com.example.cinema.model.MovieModel

class MovieShopAdapter(val lists : List<MovieModel> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var binding: LayoutMovieShowBinding
    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = LayoutMovieShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  MovieViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return  lists.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = lists[position]
        binding.root.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailMovieActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
        binding.apply {
            avgRate.text = movie.avgRate.toString()
            Glide.with(holder.itemView.context).load(movie.poster).into(binding.avt)
            amountWatch.text = movie.mountWatch.toString()
            name.text = movie.name
            time.text = movie.time.toString()
            type.text = movie.movieType
        }
        click(movie)
    }

    private fun click(movie: MovieModel) {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context,DetailMovieActivity::class.java)
            intent.putExtra("movie",movie)
            binding.root.context.startActivity(intent)
        }
    }
}