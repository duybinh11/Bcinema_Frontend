package com.example.cinema.api

import com.example.cinema.model.MovieModel
import com.example.cinema.model.UserModel
import com.example.cinema.pojo.TicketAllResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.time.LocalDate

interface ApiHome {
    @GET("api/home/get_movie_show")
    suspend fun getMovieShow() : List<MovieModel>

    @GET("api/home/get_movie_soon")
    suspend fun getMovieSoon() : List<MovieModel>

    @POST("api/home/get_show_time")
    suspend fun getMovieShowTime(@Body body: Map<String,String> ) : List<MovieModel>

    @GET("api/get_ticket")
    suspend fun getTicketAll() : List<TicketAllResponse>

    @POST("api/me")
    suspend fun getUser() : UserModel
}