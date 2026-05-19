package com.example.hufko.api.services.repository

import com.example.hufko.api.services.models.RestaurantItemFull
import com.example.hufko.api.services.network.RestaurantApiService

class RestaurantRepository(
    private val apiService: RestaurantApiService
) {

    // Existing method
    suspend fun getRestaurantsByCategory(category: String): List<RestaurantItemFull> {
        return try {
            val response = apiService.getRestaurantsByCategory(category)
            if (response.success) {
                response.data ?: emptyList()
            } else {
                throw Exception(response.message ?: "Failed to load restaurants")
            }
        } catch (e: Exception) {
            throw Exception("Network error: ${e.message}")
        }
    }

    // NEW: Get featured restaurants
    suspend fun getFeaturedRestaurants(category: String, featured: Boolean = true): List<RestaurantItemFull> {
        return try {
            val response = apiService.getFeaturedRestaurants(category, featured)
            if (response.success) {
                response.data ?: emptyList()
            } else {
                throw Exception(response.message ?: "Failed to load featured restaurants")
            }
        } catch (e: Exception) {
            throw Exception("Network error: ${e.message}")
        }
    }

    // NEW: Get recommended restaurants
    suspend fun getRecommendedRestaurants(category: String, recommended: Boolean = true): List<RestaurantItemFull> {
        return try {
            val response = apiService.getRecommendedRestaurants(category, recommended)
            if (response.success) {
                response.data ?: emptyList()
            } else {
                throw Exception(response.message ?: "Failed to load recommended restaurants")
            }
        } catch (e: Exception) {
            throw Exception("Network error: ${e.message}")
        }
    }
}