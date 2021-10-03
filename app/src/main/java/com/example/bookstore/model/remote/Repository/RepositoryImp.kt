package com.example.bookstore.model.remote.Repository

import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.data.RegistrationItem
import com.example.bookstore.model.remote.BookShopAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RepositoryImp(var api: BookShopAPI) : ServiceAPI {

    override suspend fun getAllBooks(): Response<List<BookShopItem>> {
        return withContext(Dispatchers.IO) {
            api.getAllBooks()
        }
    }

    override suspend fun loginUser(email: String, password: String) = withContext(Dispatchers.IO) {
        api.loginUser(email,password)
    }

    override suspend fun registrationUser(registrationItem: RegistrationItem) = withContext(Dispatchers.IO){
        api.registrationUser(registrationItem)
    }
}