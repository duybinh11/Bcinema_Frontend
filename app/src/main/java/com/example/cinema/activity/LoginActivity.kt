package com.example.cinema.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinema.databinding.ActivityLoginBinding
import com.example.cinema.repository.LoginRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.LoginViewModel
import com.example.demo1.util.Resource

class LoginActivity : AppCompatActivity() {
   private lateinit var binding : ActivityLoginBinding
   private lateinit var viewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()

        btnLogin()
        liveLogin()

    }
    private fun btnLogin(){
        binding.btnLogin.setOnClickListener {
            viewModel.login(binding.email.text.toString(),binding.password.text.toString())

        }
    }
    private fun liveLogin(){
        viewModel.stateLogin.observe(this, Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    notLoading()
                }
                is Resource.Error ->{
                    notLoading()
                    Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
                }
                else ->{}
            }
        })
    }
    private fun initViewModel(){
        val repository = LoginRepository(this)
        val myViewModelFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this,myViewModelFactory)[LoginViewModel::class.java]
    }

    private fun isLoading(){
        binding.apply {
            loading.visibility = View.VISIBLE
            body.visibility = View.INVISIBLE
        }
    }
    private fun notLoading(){
        binding.apply {
            loading.visibility = View.INVISIBLE
            body.visibility = View.VISIBLE
        }
    }



}