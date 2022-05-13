package com.akash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akash.myassignment.Networking.ApiService
import com.akash.myassignment.Repository.UserRepository
import com.akash.myassignment.ViewModel.MainViewModel
import com.akash.myassignment.adapter.PaginationAdapter
import com.akash.myassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

      lateinit var mainViewModel: MainViewModel
      lateinit var binding: ActivityMainBinding
      val adapter=PaginationAdapter()

     val TAG="Something wnt wrong"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter=adapter

        val apiService=ApiService.getInstance()
        val userRepository=UserRepository(apiService)

        mainViewModel=ViewModelProvider(this, UserFactoryViewModel(userRepository)).get(MainViewModel::class.java)

       mainViewModel.topheadlines.observe(this, Observer {
           if (it.totalResults!=null){
               hideProgressbar()
               adapter.differ.submitList(it.articles.toList())

           }else{
               showProgress()
           }
       })

      binding.recyclerview.addOnScrollListener(this@MainActivity.scrollListener)
    }

    private fun showProgress() {
        binding.progressbar.visibility=View.VISIBLE
        isLoading=true
    }

    private fun hideProgressbar() {
       binding.progressbar.visibility=View.INVISIBLE
        isLoading=true
    }

    var isLoading=false
    var isLastPage=false
    var isScrolling=false

    val scrollListener=object:RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager=recyclerView.layoutManager as LinearLayoutManager
            val firstItemVisiblePosition=layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount=layoutManager.childCount
            val totalItemCount=layoutManager.itemCount

            val isNotLoadingAndNotLastPage=!isLoading && !isLastPage
            val isAtLastItem=firstItemVisiblePosition+visibleItemCount >=totalItemCount
            val isNotBeginning=firstItemVisiblePosition>=0
            val isTotalMoreThenVisible=totalItemCount>=6

            val shouldPagination=isNotLoadingAndNotLastPage && isAtLastItem && isNotBeginning
                    && isTotalMoreThenVisible && isScrolling
            if (shouldPagination){
                mainViewModel.topheadlines
                isScrolling=false
            }else{
                binding.recyclerview.setPadding(0,0,0,0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling=true
        }

    }

    }


