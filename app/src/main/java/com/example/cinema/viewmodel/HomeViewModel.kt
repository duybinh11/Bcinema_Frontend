package com.example.cinema.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.model.MovieModel
import com.example.cinema.model.UserModel
import com.example.cinema.pojo.TicketAllResponse
import com.example.cinema.repository.HomeRepository
import com.example.demo1.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val movieShow = MutableLiveData<Resource<List<MovieModel>>>()
    val stateMovieShow : LiveData<Resource<List<MovieModel>>> = movieShow

    private val movieSoon = MutableLiveData<Resource<List<MovieModel>>>()
    val stateMovieSoon : LiveData<Resource<List<MovieModel>>> = movieSoon

    private val movieShowTime = MutableLiveData<Resource<List<MovieModel>>>()
    val stateMovieShowTime : LiveData<Resource<List<MovieModel>>> = movieShowTime

    private val listTicket = MutableLiveData<Resource<List<TicketAllResponse>>>()
    val stateListTicket : LiveData<Resource<List<TicketAllResponse>>> = listTicket

    private val user = MutableLiveData<UserModel>()
    val stateUser : LiveData<UserModel> = user

    fun getTicketAll(){
        listTicket.value = Resource.Loading()
        viewModelScope.launch {
            val result = homeRepository.getTicketAll()
            Log.d("apiT","$result")
            if(result.isEmpty()){
                listTicket.value = Resource.Empty()
            }else{
                listTicket.value = Resource.Success(result)
            }
        }
    }

     fun getShowSoon(){
        movieShow.value = Resource.Loading()
        viewModelScope.launch {
            val showAsync = async { homeRepository.getMovieShow() }
            val soonAsync = async { homeRepository.getMovieSoon() }
            val userAsync = async { homeRepository.getUser() }

            movieShow.value = Resource.Success(showAsync.await())
            movieSoon.value = Resource.Success(soonAsync.await())
            user.value = userAsync.await()
        }
    }

    fun getMovieShowTime(time : String){
        movieShowTime.value = Resource.Loading()

        viewModelScope.launch {
            val result = homeRepository.getMovieShowTime(time)
            if(result.isEmpty()){
                movieShowTime.value = Resource.Empty()
            }else{
                movieShowTime.value = Resource.Success(result)
            }

        }
    }


}