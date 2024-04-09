package com.example.cinema.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinema.databinding.ActivitySignUpBinding
import com.example.cinema.model.UserModel
import com.example.cinema.pojo.RegistRequest
import com.example.cinema.repository.SignUpRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.SignUpViewModel
import com.example.demo1.util.Resource


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        clickRegist()
        liveRegist()


    }
    private fun liveRegist(){
        viewModel.stateRegist.observe(this, Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
                }
                is Resource.Error ->{
                    notLoading()
                    Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                }
                else ->{}
            }
        })
    }
    private fun clickRegist() {

        binding.btnRegist.setOnClickListener {
            val request = RegistRequest(
                username = binding.username.text.toString(),
                phone = binding.phone.text.toString(),
                email = binding.email.text.toString(),
                password = binding.pass.text.toString(),
                address = binding.address.text.toString(),
                old = binding.old.text.toString().toInt()
            )
            Log.d("apiT","$request")
            viewModel.registAccount(request)
        }
    }

    private fun initViewModel() {
        val repository = SignUpRepository()
        val myViewModelFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this, myViewModelFactory)[SignUpViewModel::class.java]
    }

    private fun isLoading() {
        binding.apply {
            body.visibility = View.INVISIBLE
            loading.visibility = View.VISIBLE
        }
    }

    private fun notLoading() {
        binding.apply {
            body.visibility = View.VISIBLE
            loading.visibility = View.INVISIBLE
        }
    }
}