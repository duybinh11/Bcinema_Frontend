package com.example.cinema.repository

import android.content.Context
import com.example.cinema.api.ApiLogin
import com.example.cinema.pojo.LoginRequest
import com.example.cinema.pojo.LoginResponse
import com.example.cinema.util.RetrofitInstance
import com.example.demo1.util.ApiResponse
import retrofit2.Retrofit
import retrofit2.create

class LoginRepository(val context: Context) {
    val apiLogin: ApiLogin = RetrofitInstance.getRetrofitInstance().create(ApiLogin::class.java)

    suspend fun login(request : LoginRequest) : ApiResponse<LoginResponse> {
        return apiLogin.login(request)
    }
    fun saveToken(token: String) {
        val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }
}