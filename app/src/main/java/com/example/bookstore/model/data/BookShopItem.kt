package com.example.bookstore.model.data

data class BookShopItem(
    val author: String,
    val department: String,
    val image: String,
    val id: String,
    val language: String,
    val title: String,
    val pages: Int,
    val quotation: String,
    val reviews: Int
)