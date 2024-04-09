package com.example.cinema.util

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        fun getRetrofitInstanceToken(context: Context): Retrofit {
            val baseUrl = "http://10.0.2.2:8000/"
            val token = TokenAccount.getToken(context)
            if (token != "") {
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor( AuthInterceptor(token!!))
                    .build()
                return Retrofit.Builder().baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()
            }
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
        fun getRetrofitInstance(): Retrofit {
            val baseUrl = "http://10.0.2.2:8000/"
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()
        }
    }
}