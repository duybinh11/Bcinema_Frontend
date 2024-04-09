package com.example.demo1.util

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    val status : Boolean? = null,
    val data: T? = null,
    val error:String? = null,
    @SerializedName("current_page")
    val page :Int? = null
)