package com.example.cinema.util

import android.content.Context

class TokenAccount {
    companion object{
         fun getToken(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            return sharedPreferences.getString("token", "");
        }
        fun deleteToken(context: Context) {
            val sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove("token").apply()
        }


    }
}