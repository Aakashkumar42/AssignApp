package com.akash.myassignment.data

data class NewsList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)