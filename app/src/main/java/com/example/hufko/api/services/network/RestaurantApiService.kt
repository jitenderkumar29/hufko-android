// com/example/hufko/api/services/network/RestaurantApiService.kt
package com.example.hufko.api.services.network

import com.example.hufko.api.services.models.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApiService {

    @GET("api/restaurants/category/{category}")
    suspend fun getRestaurantsByCategory(
        @Path("category") category: String
    ): RestaurantResponse

    @GET("api/restaurants/all")
    suspend fun getAllRestaurants(): RestaurantResponse

    @GET("api/restaurants/top-rated")
    suspend fun getTopRatedRestaurants(
        @Query("topRated") topRated: Boolean = true
    ): RestaurantResponse

    @GET("api/restaurants/pure-veg")
    suspend fun getPureVegRestaurants(): RestaurantResponse

    @GET("api/restaurants/rating/{minRating}")
    suspend fun getRestaurantsByRating(
        @Path("minRating") minRating: String
    ): RestaurantResponse

    @GET("api/restaurants/search")
    suspend fun searchRestaurants(
        @Query("q") query: String
    ): RestaurantResponse
}