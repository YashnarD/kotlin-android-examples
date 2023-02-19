package com.example.livedataviewmodel.models

import com.example.livedataviewmodel.models.Address
import com.example.livedataviewmodel.models.Company

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