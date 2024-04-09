package com.example.cinema.api

import com.example.cinema.pojo.PaymentRequest
import com.example.demo1.util.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiPayment {
    @POST("api/buy_ticket")
    suspend fun buyTick(@Body response: PaymentRequest) : ApiResponse<Int>
}