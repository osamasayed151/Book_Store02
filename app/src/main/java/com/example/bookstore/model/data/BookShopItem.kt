package com.example.bookstore.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BookShopItem(
    val author: String,
    val department: String,
    val image: String,
    val id: String,
    val language: String,
    val title: String,
    val pages: Int,
    val description: String,
    val reviews: Int
): Serializable