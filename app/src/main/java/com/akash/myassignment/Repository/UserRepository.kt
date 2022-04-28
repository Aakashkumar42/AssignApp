package com.akash.myassignment.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akash.myassignment.Model.Users
import com.akash.myassignment.Model.UsersItem
import com.akash.myassignment.Networking.ApiService

class UserRepository(private val apiService: ApiService) {

    private val userLiveData=MutableLiveData<Users>()
    val user:LiveData<Users>
    get() = userLiveData

    suspend fun getAllUsers() {
        val result = apiService.getUsers()
        if (result.body()!=null){
            userLiveData.postValue(result.body())
        }
    }
}