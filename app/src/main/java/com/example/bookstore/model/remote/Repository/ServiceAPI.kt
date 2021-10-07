package com.example.bookstore.model.remote.Repository

import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.data.Data
import com.example.bookstore.model.data.User
import retrofit2.Response

interface ServiceAPI {

    suspend fun getNovels(): Response<List<BookShopItem>>


    suspend fun getProfile(authorization: String): Response<User>


    suspend fun postRegister( data: Data): Response<User>


    suspend fun postLogin( data: Data): Response<User>

    suspend fun postLogout(authorization: String): Response<User>
}