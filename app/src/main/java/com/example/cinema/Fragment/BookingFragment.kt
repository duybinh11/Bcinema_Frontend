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
import com.example.cinema.adapter.MovieScheduleAdapter
import com.example.cinema.adapter.ScheduleAdapter
import com.example.cinema.databinding.FragmentBookingBinding
import com.example.cinema.repository.HomeRepository
import com.example.cinema.util.MyViewModelFactory
import com.example.cinema.viewmodel.HomeViewModel
import com.example.demo1.util.Resource
import java.time.LocalDate


class BookingFragment : Fragment() {
    private lateinit var binding : FragmentBookingBinding
    private lateinit var viewModel : HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingBinding.inflate(layoutInflater)
        initViewModel()
        initSchedule()
        viewModel.getMovieShowTime(LocalDate.now().toString())
        liveMovieShowTime()
        return binding.root
    }

    private fun initSchedule() {
        val adapter = ScheduleAdapter(viewModel)
        binding.rccvSchedule.adapter = adapter
        binding.rccvSchedule.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }

    private fun initViewModel(){
        val homeRepository = HomeRepository(requireContext())
        val myViewModelFactory = MyViewModelFactory(homeRepository)
        viewModel = ViewModelProvider(requireActivity(),myViewModelFactory)[HomeViewModel::class.java]
    }
    private fun liveMovieShowTime(){
        viewModel.stateMovieShowTime.observe(requireActivity(), Observer {
            when(it){
                is Resource.Loading ->{
                    isLoading()
                }
                is Resource.Success ->{
                    notLoading()
                    binding.rccvMovie.adapter = MovieScheduleAdapter(it.data)
                    binding.rccvMovie.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                }
                is Resource.Error ->{
                    notLoading()
                    Toast.makeText(requireContext(),"Erorr",Toast.LENGTH_LONG).show()
                }
                is Resource.Empty ->{
                    Toast.makeText(requireContext(),"Empty",Toast.LENGTH_SHORT).show()
                    isEmptyShowTime()
                }
            }
        })
    }

    private fun isEmptyShowTime() {
        binding.apply {
            loading.visibility = View.INVISIBLE
            rccvMovie.visibility = View.INVISIBLE
            empty.visibility = View.VISIBLE
        }
    }

    private fun isLoading(){
        binding.apply {
            loading.visibility = View.VISIBLE
            rccvMovie.visibility = View.INVISIBLE
            empty.visibility = View.INVISIBLE
        }
    }
    private fun notLoading(){
        binding.apply {
            rccvMovie.visibility = View.VISIBLE
            loading.visibility = View.INVISIBLE
            empty.visibility = View.INVISIBLE
        }
    }
}