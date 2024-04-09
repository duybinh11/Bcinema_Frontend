package com.example.cinema.repository

import android.content.Context
import com.example.cinema.api.ApiPayment
import com.example.cinema.pojo.PaymentRequest
import com.example.cinema.util.RetrofitInstance
import com.example.demo1.util.ApiResponse

class PaymentRepository(val context: Context) {
    val api : ApiPayment = RetrofitInstance.getRetrofitInstanceToken(context).create(ApiPayment::class.java)

    suspend fun buyTicket(request: PaymentRequest) : ApiResponse<Int>{
        return api.buyTick(request)
    }

}