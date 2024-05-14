package com.example.cinema.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.cinema.Fragment.BookingFragment
import com.example.cinema.Fragment.HomeFragment
import com.example.cinema.Fragment.MovieFragment
import com.example.cinema.Fragment.ProfileFragment
import com.example.cinema.Fragment.TicketFragment
import com.example.cinema.R
import com.example.cinema.adapter.PagerAdapter
import com.example.cinema.databinding.ActivityHomeBinding
import com.example.cinema.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val list = listOf<Fragment>(HomeFragment(),BookingFragment(),TicketFragment(),MovieFragment(),ProfileFragment())
        binding.viewpager.adapter = PagerAdapter(list,supportFragmentManager,lifecycle)
        binding.viewpager.isUserInputEnabled = false

        val moveToSecondFragment = intent?.getBooleanExtra("moveToSecondFragment", false)
        if (moveToSecondFragment == true) {
            moveToSecondFragment()
        }

        binding.nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> binding.viewpager.currentItem = 0
                R.id.nav_booking -> binding.viewpager.currentItem = 1
                R.id.nav_ticket -> binding.viewpager.currentItem = 2
                R.id.nav_movie -> binding.viewpager.currentItem = 3
                R.id.nav_user -> binding.viewpager.currentItem = 4
            }
            true
        }

        setContentView(binding.root)
    }
    fun moveToSecondFragment() {
        binding.viewpager.currentItem = 1
        binding.nav.selectedItemId = R.id.nav_booking
    }
}