package com.example.cinema.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinema.repository.HomeRepository
import com.example.cinema.repository.LoginRepository
import com.example.cinema.repository.MyTicketRepository
import com.example.cinema.repository.PaymentRepository
import com.example.cinema.repository.SeatRepository
import com.example.cinema.repository.SignUpRepository
import com.example.cinema.viewmodel.HomeViewModel
import com.example.cinema.viewmodel.LoginViewModel
import com.example.cinema.viewmodel.MyTicketViewModel
import com.example.cinema.viewmodel.PaymentViewModel
import com.example.cinema.viewmodel.SeatViewModel
import com.example.cinema.viewmodel.SignUpViewModel


class MyViewModelFactory(private val repository: Any) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository as LoginRepository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository as SignUpRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository as HomeRepository) as T
            }
            modelClass.isAssignableFrom(SeatViewModel::class.java) -> {
                SeatViewModel(repository as SeatRepository) as T
            }
            modelClass.isAssignableFrom(PaymentViewModel::class.java) -> {
                PaymentViewModel(repository as PaymentRepository) as T
            }
            modelClass.isAssignableFrom(MyTicketViewModel::class.java) -> {
                MyTicketViewModel(repository as MyTicketRepository) as T
            }

            else ->{
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

    }
}