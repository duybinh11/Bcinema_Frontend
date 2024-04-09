package com.example.cinema.api

import com.example.cinema.pojo.RegistRequest
import com.example.demo1.util.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiSignUp {
    @POST("api/regist")
    suspend fun registAccount(@Body request: RegistRequest) : ApiResponse<Boolean>
}