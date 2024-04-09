package com.example.cinema.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.pojo.RegistRequest
import com.example.cinema.repository.SignUpRepository
import com.example.demo1.util.Resource
import kotlinx.coroutines.launch

class SignUpViewModel(val signUpRepository: SignUpRepository) : ViewModel(){
    private val _registAccount = MutableLiveData<Resource<Boolean>>()
    val stateRegist : LiveData<Resource<Boolean>> = _registAccount

    fun registAccount(request: RegistRequest){
        _registAccount.value = Resource.Loading()
        viewModelScope.launch {
            val result = signUpRepository.registerAccount(request)
            if (result.status!!){
                _registAccount.value = Resource.Success(true)
            }else{
                _registAccount.value = Resource.Error("loi dang ky")
            }
        }
    }
}