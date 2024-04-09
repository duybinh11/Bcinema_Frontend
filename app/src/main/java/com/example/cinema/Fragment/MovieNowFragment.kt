package com.example.cinema.Fragment

import android.os.Bundle
import android.util.Log
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
import com.example.cinema.adapter.MovieFilmAdapter
import com.example.cinema.adapter.MovieNowSoonAdapter
import com.example.cinema.adapter.MovieShopAdapter
import com.example.cinema.databinding.FragmentMovieNowBinding
import com.example.cinema.repository.HomeRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.HomeViewModel
import com.example.demo1.util.Resource


class MovieNowFragment : Fragment() {
    private lateinit var binding : FragmentMovieNowBinding
    private lateinit var viewModel : HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieNowBinding.inflate(layoutInflater)
        initViewModel()
        liveMovieShow()
        return binding.root
    }

    private fun initViewModel(){
        val homeRepository = HomeRepository(requireContext())
        val myViewModelFactory = MyViewModelFactory(homeRepository)
        viewModel = ViewModelProvider(requireActivity(),myViewModelFactory)[HomeViewModel::class.java]
    }

    private fun liveMovieShow(){
        viewModel.stateMovieShow.observe(requireActivity(), Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    binding.gridview.adapter = MovieFilmAdapter(requireContext(), it.data)
                }
                is Resource.Error ->{
                    notLoading()
                    Toast.makeText(requireContext(),"Erorr", Toast.LENGTH_LONG).show()
                }
                else ->{}
            }
        })
    }

    private fun notLoading() {
        binding.apply {
            loading.visibility = View.INVISIBLE
            gridview.visibility = View.VISIBLE
        }
    }

    private fun isLoading() {
        binding.apply {
            loading.visibility = View.VISIBLE
            gridview.visibility = View.INVISIBLE
        }
    }


}