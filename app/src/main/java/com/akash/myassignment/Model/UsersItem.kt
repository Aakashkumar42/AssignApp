package com.akash.myassignment.Model

data class UsersItem(
    val id: Int,
    val name: String,
    val profileImage: String,
    val qualification: List<String>,
    val subjects: List<String>
)