package com.example.cinema.repository

import com.example.cinema.api.ApiSeat
import com.example.cinema.model.SeatModel
import com.example.cinema.util.RetrofitInstance

class SeatRepository {
    val apiSeat : ApiSeat = RetrofitInstance.getRetrofitInstance().create(ApiSeat::class.java)

    suspend fun getSeatBooked(idShowTime : Int) : List<SeatModel>{
        return apiSeat.getSeatBooked(idShowTime)
    }
}