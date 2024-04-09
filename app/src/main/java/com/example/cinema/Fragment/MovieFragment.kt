package com.example.cinema.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinema.R
import com.example.cinema.adapter.MovieNowSoonAdapter
import com.example.cinema.databinding.FragmentMovieBinding
import com.google.android.material.tabs.TabLayoutMediator

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(layoutInflater)

        val adapter = MovieNowSoonAdapter(listOf(MovieNowFragment(),MovieSoonFragment()),requireActivity().supportFragmentManager,requireActivity().lifecycle)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            if(position == 0){
                tab.text = "Đang chiếu"
            }else{
                tab.text = "Sắp chiếu"
            }
        }.attach()
        return binding.root
    }

}