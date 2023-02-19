package com.example.coroutines.models

import com.example.coroutines.models.Address
import com.example.coroutines.models.Company

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)