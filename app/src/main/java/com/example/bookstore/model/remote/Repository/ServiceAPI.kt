package com.example.bookstore.model.remote.Repository

import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.data.RegistrationItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {

    suspend fun getAllBooks(): Response<List<BookShopItem>>

    suspend fun loginUser(email: String, password: String): Response<RegistrationItem>

    suspend fun registrationUser(registrationItem: RegistrationItem): Response<RegistrationItem>
}