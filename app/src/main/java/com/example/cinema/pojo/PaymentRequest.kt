package com.example.cinema.pojo

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("show_time")
    val idShowTime : Int,
    @SerializedName("total_price")
    val totalPrice : Int,
    @SerializedName("seats")
    val listSeat : List<Int>
)