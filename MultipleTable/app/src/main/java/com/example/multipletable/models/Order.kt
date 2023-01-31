package com.example.multipletable.models

data class Order(val id: Int = 0, val customer: Customer, val employee: Employee, val orderDate: String)
