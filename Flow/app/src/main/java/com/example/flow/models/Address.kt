package com.example.flow.models

import com.example.flow.models.Geo

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)