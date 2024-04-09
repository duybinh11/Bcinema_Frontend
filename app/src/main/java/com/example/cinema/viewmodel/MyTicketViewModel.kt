package com.example.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.model.TicketModel
import com.example.cinema.pojo.MyTicketResponse
import com.example.cinema.repository.MyTicketRepository
import com.example.demo1.util.Resource
import kotlinx.coroutines.launch

class MyTicketViewModel(val repository: MyTicketRepository) : ViewModel() {
    private val ticket = MutableLiveData<Resource<MyTicketResponse>>()
    val stateTicket : LiveData<Resource<MyTicketResponse>> = ticket

    fun getATicket(id : Int){
        ticket.value = Resource.Loading()
        viewModelScope.launch {
            val result = repository.getATicket(id)
            ticket.value = Resource.Success(result)
        }
    }
}