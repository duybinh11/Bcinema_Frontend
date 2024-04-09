package com.example.cinema.api

import com.example.cinema.pojo.LoginRequest
import com.example.cinema.pojo.LoginResponse
import com.example.demo1.util.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiLogin {

    @POST("api/login")
    suspend fun login(@Body data: LoginRequest) : ApiResponse<LoginResponse>
}