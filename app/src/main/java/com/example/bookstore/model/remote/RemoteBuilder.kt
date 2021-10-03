package com.example.bookstore.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteBuilder {

    companion object{
        //                                    https://61562057e039a0001725a91d.mockapi.io/book/v1/
        private const val BASE_URL: String = "https://61562057e039a0001725a91d.mockapi.io/book/v1/"

        fun builder():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}