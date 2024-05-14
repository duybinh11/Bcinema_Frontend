package com.example.cinema.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cinema.databinding.ActivityDetailMovieBinding
import com.example.cinema.databinding.LayoutTrailerBinding
import com.example.cinema.model.MovieModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMovieBinding
    private lateinit var movie : MovieModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataIntent()
        showMovie()
        showTrailer()
        binding.btnBooking.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("moveToSecondFragment", true)
            startActivity(intent)
            finish()
        }
    }

    private fun showTrailer() {
        val binding1 = LayoutTrailerBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this).setView(binding1.root)
        val alertDialog : AlertDialog = builder.create()
        if(alertDialog.window != null){
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }



        binding.btnTrailer.setOnClickListener {

            val videoId = movie.trailer.split('=')[1].split('&')[0]
            alertDialog.show()
            Log.d("value_test","$videoId")
            val youTubePlayerView: YouTubePlayerView = binding1.yt
            lifecycle.addObserver(youTubePlayerView);

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0f);
                }
            })
        }

    }


    private fun showMovie() {
        Glide.with(this).load(movie.poster).into(binding.avt)
        binding.apply {
            amountWatch.text = movie.mountWatch.toString()
            time.text = movie.time.toString()
            day.text = movie.dateStart
            language.text = movie.language
            rate.text = movie.avgRate.toString()
//            story.text = movie.
            name.text = movie.name
            old.text = "${movie.censoship}+"
            type.text = movie.movieType
        }
    }

    private fun getDataIntent() {
        movie = intent.getParcelableExtra<MovieModel>("movie")!!
    }

}