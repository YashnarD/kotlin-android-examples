package com.example.retrofitreqresin.models.list_users

import com.example.retrofitreqresin.models.list_users.Data
import com.example.retrofitreqresin.models.list_users.Support

data class UserData(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)