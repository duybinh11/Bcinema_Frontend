package com.example.cinema.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.model.SeatModel
import com.example.cinema.repository.SeatRepository
import com.example.demo1.util.Resource
import kotlinx.coroutines.launch

class SeatViewModel(val repository: SeatRepository) : ViewModel() {
    private val listSeat = MutableLiveData<MutableList<Int>>(mutableListOf())
    private val listSeatBooked =MutableLiveData<Resource<List<SeatModel>>>()

    val stateSeatBooked : LiveData<Resource<List<SeatModel>>> = listSeatBooked

    val stateCost: MutableLiveData<MutableList<Int>> = listSeat

    fun getSeatBooked(idShowTime : Int){
        listSeatBooked.value = Resource.Loading()
        viewModelScope.launch {
            val result = repository.getSeatBooked(idShowTime)
            listSeatBooked.value = Resource.Success(result)
        }
    }


    fun addSeat(seat: Int) {
        listSeat.value?.add(seat)
        listSeat.postValue(listSeat.value)
    }
    fun removeSeat(seat: Int) {
        listSeat.value?.remove(seat)
        listSeat.postValue(listSeat.value)
    }
    fun resetSeat(){
        listSeat.value?.clear()
    }



}