package com.example.cinema.api

import com.example.cinema.model.TicketModel
import com.example.cinema.pojo.MyTicketResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMyTicket {
    @GET("api/get_a_ticket/{id_ticket}")
    suspend fun getATicket(@Path("id_ticket") id : Int) : MyTicketResponse
}