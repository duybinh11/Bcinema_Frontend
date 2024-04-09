package com.example.cinema.pojo

import com.example.cinema.model.MovieModel
import com.example.cinema.model.ShowTimeModel
import com.example.cinema.model.TicketModel
import com.google.gson.annotations.SerializedName

data class TicketAllResponse (
    val ticket : TicketModel,
    val movie : MovieModel,
    @SerializedName("show_time")
    val showTime : ShowTimeModel
)