package com.example.cinema.model

import com.google.gson.annotations.SerializedName

data class SeatModel (
    val id : Int,
    @SerializedName("number_seat")
    val numberSeat : Int
)
