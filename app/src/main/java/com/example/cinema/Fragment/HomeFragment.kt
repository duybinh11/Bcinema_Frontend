package com.example.cinema.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.R
import com.example.cinema.adapter.MovieComingAdapter
import com.example.cinema.adapter.MovieShopAdapter
import com.example.cinema.databinding.FragmentHomeBinding
import com.example.cinema.repository.HomeRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.HomeViewModel
import com.example.demo1.util.Resource
import java.time.LocalDate


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        initViewModel()
        getMovie()
        liveUsername()
        liveMovieShow()
        liveMovieSoon()
        
        return binding.root
    }

    private fun liveUsername() {
        viewModel.stateUser.observe(requireActivity(), Observer {
            binding.username.text = it.username
        })
    }

    private fun initViewModel(){
        val homeRepository = HomeRepository(requireContext())
        val myViewModelFactory = MyViewModelFactory(homeRepository)
        viewModel = ViewModelProvider(requireActivity(),myViewModelFactory)[HomeViewModel::class.java]
    }
    private fun getMovie(){
        viewModel.getShowSoon()
    }
    private fun liveMovieShow(){
        viewModel.stateMovieShow.observe(requireActivity(), Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    binding.pager.adapter = MovieShopAdapter(it.data)
                    binding.dotsIndicator.attachTo(binding.pager)
                }
                is Resource.Error ->{
                    notLoading()
                    Toast.makeText(requireContext(),"Erorr",Toast.LENGTH_LONG).show()
                }
                else ->{}
            }
        })
    }
    private fun liveMovieSoon(){
        viewModel.stateMovieSoon.observe(requireActivity(), Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    binding.rccv.adapter = MovieComingAdapter(it.data)
                    binding.rccv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                }
                is Resource.Error ->{
                    notLoading()
                    Toast.makeText(requireContext(),"Erorr",Toast.LENGTH_LONG).show()
                }
                else ->{}
            }
        })
    }
    private fun isLoading(){
        binding.apply {
            body.visibility = View.INVISIBLE
            loading.visibility = View.VISIBLE
        }
    }
    private fun notLoading(){
        binding.apply {
            body.visibility = View.VISIBLE
            loading.visibility = View.INVISIBLE
        }
    }


}