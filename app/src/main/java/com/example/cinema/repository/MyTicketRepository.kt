package com.example.cinema.repository

import android.content.Context
import com.example.cinema.api.ApiMyTicket
import com.example.cinema.model.TicketModel
import com.example.cinema.pojo.MyTicketResponse
import com.example.cinema.util.RetrofitInstance

class MyTicketRepository(val context: Context) {
    val apiMyTicket : ApiMyTicket = RetrofitInstance.getRetrofitInstanceToken(context).create(ApiMyTicket::class.java)

    suspend fun getATicket(id : Int) : MyTicketResponse{
        return apiMyTicket.getATicket(id)
    }
}