package com.example.cinema.pojo

import com.example.cinema.model.MovieModel
import com.example.cinema.model.TicketModel

data class MyTicketResponse(
    val movie : MovieModel,
    val ticket : TicketModel
)