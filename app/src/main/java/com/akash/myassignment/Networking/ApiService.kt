package com.akash.myassignment.Networking

import com.akash.myassignment.Model.Users
import com.akash.myassignment.Model.UsersItem
import com.akash.myassignment.data.NewsList
import com.akash.myassignment.utlis.Constants.Companion.API_KEY
import com.akash.myassignment.utlis.Constants.Companion.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //news fetch function
  @GET("v2/top-headlines?")
  suspend fun getUsers(
        @Query("page")page:Int,
        @Query("apiKey")apiKey:String=API_KEY
    ):Response<NewsList>

    @GET("v2/top-headlines?country=in&apiKey=37a1f12d03514b5f89f5f0fec848729e")
    suspend fun getToheadlines():Response<NewsList>

    //news fetching instance object
  companion object{
      var apiService:ApiService?=null
      fun getInstance():ApiService{

          val retrofit=Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build()
          apiService=retrofit.create(ApiService::class.java)
          return apiService!!
          }
  }
}