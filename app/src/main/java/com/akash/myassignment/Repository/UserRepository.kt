package com.akash.myassignment.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.akash.myassignment.Model.Users
import com.akash.myassignment.Model.UsersItem
import com.akash.myassignment.Networking.ApiService
import com.akash.myassignment.data.NewsList

class UserRepository(private val apiService: ApiService) {

    private val userLiveData=MutableLiveData<NewsList>()
    val user:LiveData<NewsList>
    get() = userLiveData

    suspend fun getAllUsers() {
       val result= apiService.getToheadlines()
        if (result.body()!=null){
            userLiveData.postValue(result.body())
        }
    }
    }
