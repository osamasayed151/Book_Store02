package com.example.bookstore.model.remote.Repository

import com.example.bookstore.model.data.BookShopItem
import com.example.bookstore.model.data.Data
import com.example.bookstore.model.data.User
import com.example.bookstore.model.remote.BookShopAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RepositoryImp(var api: BookShopAPI) : ServiceAPI {

    override suspend fun getNovels(): Response<List<BookShopItem>> {
        return withContext(Dispatchers.IO) {
            api.getNovels()
        }
    }

    override suspend fun getProfile(authorization: String): Response<User> {
        return withContext(Dispatchers.IO){
            api.getProfile(authorization)
        }
    }

    override suspend fun postRegister(data: Data): Response<User>{
        return withContext(Dispatchers.IO){
            api.postRegister(data)
        }
    }

    override suspend fun postLogin(data: Data): Response<User> {
        return withContext(Dispatchers.IO){
            api.postLogin(data)
        }
    }

    override suspend fun postLogout(authorization: String): Response<User> {
        return withContext(Dispatchers.IO){
            api.postLogout(authorization)
        }
    }

}