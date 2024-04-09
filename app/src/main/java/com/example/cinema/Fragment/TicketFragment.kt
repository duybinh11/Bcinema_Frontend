package com.example.cinema.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinema.R
import com.example.cinema.adapter.TicketAdapter
import com.example.cinema.databinding.FragmentTicketBinding
import com.example.cinema.model.MovieModel
import com.example.cinema.repository.HomeRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.HomeViewModel
import com.example.demo1.util.Resource


class TicketFragment : Fragment() {
    private lateinit var binding : FragmentTicketBinding
    private lateinit var viewModel : HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketBinding.inflate(layoutInflater)
        initViewModel()
        viewModel.getTicketAll()
        liveTicketAll()

        return binding.root
    }

    private fun liveTicketAll() {
        viewModel.stateListTicket.observe(requireActivity(), Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    binding.rccv.adapter = TicketAdapter(it.data)
                    binding.rccv.layoutManager = LinearLayoutManager(requireContext())
                }
                is Resource.Empty ->{
                    isEmptyTicket()
                }
                is Resource.Error ->{
                    notLoading()
                }
            }
        })
    }

    private fun isEmptyTicket() {
        binding.apply {
            body.visibility = View.INVISIBLE
            empty.visibility = View.VISIBLE
            rccv.visibility = View.INVISIBLE
        }
    }

    private fun notLoading() {
        binding.apply {
            body.visibility = View.VISIBLE
            empty.visibility = View.INVISIBLE
            loading.visibility = View.INVISIBLE
        }
    }

    private fun isLoading() {
        binding.apply {
            body.visibility = View.INVISIBLE
            empty.visibility = View.INVISIBLE
            loading.visibility = View.VISIBLE
        }
    }

    private fun initViewModel(){
        val homeRepository = HomeRepository(requireContext())
        val myViewModelFactory = MyViewModelFactory(homeRepository)
        viewModel = ViewModelProvider(requireActivity(),myViewModelFactory)[HomeViewModel::class.java]
    }



}