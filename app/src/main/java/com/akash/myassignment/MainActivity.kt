package com.akash.myassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.UsersAdapter
import com.akash.myassignment.Networking.ApiService
import com.akash.myassignment.Repository.UserRepository
import com.akash.myassignment.ViewModel.MainViewModel
import com.akash.myassignment.databinding.ActivityMainBinding
import okhttp3.internal.userAgent

class MainActivity : AppCompatActivity() {

      lateinit var mainViewModel: MainViewModel
      lateinit var binding: ActivityMainBinding
      val adapter=UsersAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter=adapter

        val apiService=ApiService.getInstance()
        val userRepository=UserRepository(apiService)

        mainViewModel=ViewModelProvider(this,UserFactoryViewModel(userRepository)).get(MainViewModel::class.java)


        mainViewModel.users.observe(this, Observer {
            adapter.setUser(it)
        })
    }
}