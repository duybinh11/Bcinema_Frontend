package com.example.cinema.repository

import com.example.cinema.api.ApiSignUp
import com.example.cinema.pojo.RegistRequest
import com.example.cinema.util.RetrofitInstance
import com.example.demo1.util.ApiResponse

class SignUpRepository {
    val apiSignUp : ApiSignUp = RetrofitInstance.getRetrofitInstance().create(ApiSignUp::class.java)

    suspend fun registerAccount(request: RegistRequest) : ApiResponse<Boolean>{
        return apiSignUp.registAccount(request)
    }
}