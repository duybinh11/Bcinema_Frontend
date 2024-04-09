package com.example.cinema.api

import com.example.cinema.model.SeatModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiSeat {
    @GET("api/home/get_seat_booked/{id_show_time}")
    suspend fun getSeatBooked(@Path("id_show_time") id : Int) : List<SeatModel>
}