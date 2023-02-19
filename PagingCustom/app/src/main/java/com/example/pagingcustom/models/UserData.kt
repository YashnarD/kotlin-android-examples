package com.example.pagingcustom.models

import com.example.pagingcustom.models.Data
import com.example.pagingcustom.models.Support

data class UserData(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)