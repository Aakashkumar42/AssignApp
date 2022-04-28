package com.akash.myassignment.Networking

import com.akash.myassignment.Model.Users
import com.akash.myassignment.Model.UsersItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
  @GET("/easygautam/data/users")
  suspend fun getUsers():Response<Users>

  companion object{
      var apiService:ApiService?=null
      fun getInstance():ApiService{

          val retrofit=Retrofit.Builder()
              .baseUrl("https://my-json-server.typicode.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .build()
          apiService=retrofit.create(ApiService::class.java)
          return apiService!!
          }
  }
}