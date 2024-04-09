package com.example.cinema.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.cinema.activity.DetailMovieActivity
import com.example.cinema.databinding.LayoutNowSoonBinding
import com.example.cinema.model.MovieModel

class MovieFilmAdapter(val context: Context,val list: List<MovieModel>) : BaseAdapter() {
    private lateinit var binding : LayoutNowSoonBinding
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return true
    }

    override fun getItemId(p0: Int): Long {
        return  p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val movie = list[p0]
        binding = LayoutNowSoonBinding.inflate(LayoutInflater.from(context))
        binding.apply {
            amountWatch.text = movie.mountWatch.toString()
//            avgRate.text = movie.avgRate.toString()
            Glide.with(context).load(movie.poster).into(avt)
            type.text = movie.movieType
            name.text = movie.name
            time.text = movie.time.toString()
        }
        click(movie)
        return binding.root
    }

    private fun click(movie : MovieModel) {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context,DetailMovieActivity::class.java)
            intent.putExtra("movie",movie)
            binding.root.context.startActivity(intent)
        }
    }
}