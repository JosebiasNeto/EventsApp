package com.example.eventsapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "https://5f5a8f24d44d640016169133.mockapi.io/api/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val eventsRepositoriesApi : ApiService = getRetrofit().create(ApiService::class.java)
}