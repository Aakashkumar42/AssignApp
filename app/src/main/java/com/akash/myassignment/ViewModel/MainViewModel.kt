package com.akash.myassignment.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.myassignment.Repository.UserRepository
import com.akash.myassignment.data.NewsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val userRepository: UserRepository):ViewModel() {


init {
    viewModelScope.launch(Dispatchers.IO){
        userRepository.getAllUsers()
    }

}
    val topheadlines:LiveData<NewsList>
    get() = userRepository.user
}