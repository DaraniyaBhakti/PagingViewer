package com.example.pagingviewer.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Encapsulates Retrofit setup

// This is used when we implement api without dependecy injection
// It is used to initalise api instance
object RetrofitHelper {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}