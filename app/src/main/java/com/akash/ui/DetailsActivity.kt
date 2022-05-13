package com.akash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akash.myassignment.R
import com.akash.myassignment.databinding.ActivityDetailsBinding
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras=intent.extras
        val title=extras?.getString("title")
        val description=extras?.getString("description")
        val source=extras?.getString("source")
        val pulishedat=extras?.getString("publishedAt")
        val imageUrl=extras?.getString("imageurl")
        binding.tvTitleDe.text=title
        binding.tvDescriptionDe.text=description
        binding.tvSourceDe.text=source
        binding.tvPublishedAtDe.text=pulishedat
        Glide.with(this).load(imageUrl).into(binding.ivArtimage)


        extras?.apply {
            getString("url")
            get("xxyy")
        }

    }
}