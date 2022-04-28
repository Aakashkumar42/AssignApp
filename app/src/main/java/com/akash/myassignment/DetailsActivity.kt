package com.akash.myassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akash.myassignment.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle=intent.extras

        val name= bundle?.getString("name")
        val subject= bundle?.getString("subject")
        val qualify= bundle?.getString("qualify")
        val image: Int? = bundle?.getInt("image")

        if (image != null) {
            binding.ivProfile.setImageResource(image)
        }
    }
}