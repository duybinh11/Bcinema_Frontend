package com.example.cinema.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.pojo.LoginRequest
import com.example.cinema.repository.LoginRepository
import com.example.demo1.util.Resource
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(val loginRepository: LoginRepository) : ViewModel() {
    private var _login = MutableLiveData<Resource<Boolean>>()
    var stateLogin : LiveData<Resource<Boolean>> = _login

    fun login(email : String,pass : String){
        val loginRequest = LoginRequest(email,pass)
        _login.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val result = loginRepository.login(loginRequest)
                Log.d("apiT","$result")
                if (result.status!!) {
                    _login.value = Resource.Success(true)
                    loginRepository.saveToken(result.data!!.token)
                } else {
                    _login.value = Resource.Error(result.error!!)
                }
            }
            catch (e: HttpException) {
                if (e.code() == 401) {
                    _login.value = Resource.Error("Unauthorized access")
                } else {
                    _login.value = Resource.Error("Other HTTP error")
                }
            } catch (e: Exception) {
                // Xử lý các lỗi khác ngoài HTTP
                _login.value = Resource.Error("An error occurred $e")
            }
        }
    }
}