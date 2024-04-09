package com.example.cinema.pojo

data class RegistRequest(
    val username : String,
    val email : String,
    val password : String,
    val phone : String,
    val address : String,
    val old : Int
)