package com.example.cinema.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cinema.R
import com.example.cinema.databinding.FragmentProfileBinding
import com.example.cinema.repository.HomeRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.HomeViewModel


class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        initViewModel()
        liveUser()
        return binding.root
    }

    private fun liveUser() {
        viewModel.stateUser.observe(requireActivity(), Observer {
            binding.apply {
                username.text = it.username
                email.text = it.email
                phone.text = it.phone
            }

        })
    }
    private fun initViewModel(){
        val homeRepository = HomeRepository(requireContext())
        val myViewModelFactory = MyViewModelFactory(homeRepository)
        viewModel = ViewModelProvider(requireActivity(),myViewModelFactory)[HomeViewModel::class.java]
    }

}