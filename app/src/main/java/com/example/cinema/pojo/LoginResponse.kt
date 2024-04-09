package com.example.cinema.pojo

import com.google.gson.annotations.SerializedName

class LoginResponse (
    @SerializedName("access_token")
    val token : String,
    @SerializedName("token_type")
    val type : String
)