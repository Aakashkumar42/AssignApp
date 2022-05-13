package com.akash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.myassignment.Repository.UserRepository
import com.akash.myassignment.ViewModel.MainViewModel

class UserFactoryViewModel(private val userRepository: UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository) as T
    }
}