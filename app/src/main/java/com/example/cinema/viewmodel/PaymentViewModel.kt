package com.example.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.pojo.PaymentRequest
import com.example.cinema.repository.PaymentRepository
import com.example.demo1.util.ApiResponse
import com.example.demo1.util.Resource
import kotlinx.coroutines.launch

class PaymentViewModel(val repository: PaymentRepository) : ViewModel(){
    private val buyTicket = MutableLiveData<Resource<Int>>()
    val stateBuyTicket : LiveData<Resource<Int>> = buyTicket

    fun buyTick(request: PaymentRequest){
        buyTicket.value = Resource.Loading()
        viewModelScope.launch {
            val result = repository.buyTicket(request)
            if(result.status!!){
                buyTicket.value = Resource.Success(result.data!!)
            }else{
                buyTicket.value = Resource.Error(result.error!!)
            }
        }
    }
}