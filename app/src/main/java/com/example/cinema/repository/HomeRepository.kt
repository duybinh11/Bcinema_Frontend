package com.example.cinema.repository

import android.content.Context
import com.example.cinema.model.MovieModel
import com.example.cinema.api.ApiHome
import com.example.cinema.model.UserModel
import com.example.cinema.pojo.TicketAllResponse
import com.example.cinema.util.RetrofitInstance
import java.time.LocalDate

class HomeRepository(val context: Context) {
    private val apiHome : ApiHome = RetrofitInstance.getRetrofitInstanceToken(context).create(ApiHome::class.java)
    suspend fun getMovieShow() : List<MovieModel>{
        return apiHome.getMovieShow()
    }

    suspend fun getMovieSoon() : List<MovieModel>{
        return apiHome.getMovieSoon()
    }

    suspend fun getMovieShowTime(time : String) : List<MovieModel> {
        return apiHome.getMovieShowTime(mapOf("time" to time))
    }
    suspend fun getTicketAll() : List<TicketAllResponse>{
        return apiHome.getTicketAll()
    }
    suspend fun getUser() : UserModel{
        return apiHome.getUser()
    }
}