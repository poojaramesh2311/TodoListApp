package com.example.todolistapp



import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory


///////////////////////retrofit////////////////////////////////////


object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com/docs"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}




