package com.example.bookstore.model.remote

import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.data.Data
import com.example.bookstore.model.data.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface BookShopAPI {

    @GET("bookshop")
    suspend fun getAllBooks(): Response<List<BookShopItem>>

    @GET("profile")
    suspend fun getProfile(@Header("Authorization") authorization: String): Response<User>

    @POST("register")
    suspend fun postRegister(@Body data: Data): Response<User>

    @POST("login")
    suspend fun postLogin(@Body data: Data): Response<User>

    @POST("logout")
    suspend fun postLogout(@Header("Authorization") authorization: String): Response<User>

}