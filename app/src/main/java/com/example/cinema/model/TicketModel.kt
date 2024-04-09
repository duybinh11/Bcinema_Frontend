package com.example.cinema.model

import com.google.gson.annotations.SerializedName

data class TicketModel(
    val id : Int,
    @SerializedName("total_price")
    val totalPrice : Int,
    @SerializedName("date_time")
    val dateTime : String,
    @SerializedName("seat")
    val listSeat : List<SeatModel>
)