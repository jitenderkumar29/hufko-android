package com.example.hufko.api.services.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.hufko.api.services.config.NetworkConfig
import com.google.gson.GsonBuilder

object DietRetrofitClient {
    // Use the API_BASE_URL from NetworkConfig
    private const val BASE_URL = NetworkConfig.API_BASE_URL

    private val gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    // Add service instance for easy access
    val dietApiService: DietApiService by lazy {
        instance.create(DietApiService::class.java)
    }
}