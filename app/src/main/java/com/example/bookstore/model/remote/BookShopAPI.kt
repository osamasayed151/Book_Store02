package com.example.bookstore.model.remote

import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.data.RegistrationItem
import retrofit2.Response
import retrofit2.http.*

interface BookShopAPI {


    @GET("bookshop")
    suspend fun getAllBooks(): Response<List<BookShopItem>>

    @GET("book/v1/registration/")
    suspend fun loginUser(@Query("email") email: String, @Query("password") password: String): Response<RegistrationItem>

    @POST("registration")
    suspend fun registrationUser(@Body registrationItem: RegistrationItem): Response<RegistrationItem>

}