package com.akash.myassignment.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.myassignment.Model.Users
import com.akash.myassignment.Model.UsersItem
import com.akash.myassignment.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val userRepository: UserRepository):ViewModel() {

init {
    viewModelScope.launch(Dispatchers.IO){

        userRepository.getAllUsers()
    }

}
    val users:LiveData<Users>
    get() = userRepository.user
}