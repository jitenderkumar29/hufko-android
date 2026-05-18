// com/example/hufko/api/services/viewmodels/RestaurantViewModel.kt
package com.example.hufko.api.services.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hufko.api.services.models.RestaurantItemFull
import com.example.hufko.api.services.models.RestaurantResponse
import com.example.hufko.api.services.network.RestaurantApiService
import com.example.hufko.api.services.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {

    private val apiService: RestaurantApiService = RetrofitClient.instance.create(RestaurantApiService::class.java)

    // State for restaurants
    private val _restaurants = MutableStateFlow<List<RestaurantItemFull>>(emptyList())
    val restaurants: StateFlow<List<RestaurantItemFull>> = _restaurants.asStateFlow()

    // State for loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // State for error
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Load restaurants by category
    fun loadRestaurantsByCategory(category: String = "ALL") {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = apiService.getRestaurantsByCategory(category)
                if (response.success) {
                    _restaurants.value = response.data ?: emptyList()
                    println("✅ Loaded ${_restaurants.value.size} restaurants for category: $category")
                } else {
                    _error.value = response.message ?: "Failed to load restaurants"
                    println("❌ Error loading restaurants: ${_error.value}")
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Network error occurred"
                println("❌ Exception loading restaurants: ${e.message}")
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Load all restaurants
    fun loadAllRestaurants() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = apiService.getAllRestaurants()
                if (response.success) {
                    _restaurants.value = response.data ?: emptyList()
                    println("✅ Loaded ${_restaurants.value.size} restaurants")
                } else {
                    _error.value = response.message ?: "Failed to load restaurants"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Network error occurred"
                println("❌ Exception: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Load top rated restaurants
    fun loadTopRatedRestaurants(topRated: Boolean = true) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = apiService.getTopRatedRestaurants(topRated)
                if (response.success) {
                    _restaurants.value = response.data ?: emptyList()
                    println("✅ Loaded ${_restaurants.value.size} top rated restaurants")
                } else {
                    _error.value = response.message ?: "Failed to load top rated restaurants"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Network error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Load restaurants by rating
    fun loadRestaurantsByRating(minRating: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = apiService.getRestaurantsByRating(minRating)
                if (response.success) {
                    _restaurants.value = response.data ?: emptyList()
                    println("✅ Loaded ${_restaurants.value.size} restaurants with rating >= $minRating")
                } else {
                    _error.value = response.message ?: "Failed to load restaurants by rating"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Network error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Load pure veg restaurants
    fun loadPureVegRestaurants() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = apiService.getPureVegRestaurants()
                if (response.success) {
                    _restaurants.value = response.data ?: emptyList()
                    println("✅ Loaded ${_restaurants.value.size} pure veg restaurants")
                } else {
                    _error.value = response.message ?: "Failed to load pure veg restaurants"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Network error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Search restaurants
    fun searchRestaurants(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = apiService.searchRestaurants(query)
                if (response.success) {
                    _restaurants.value = response.data ?: emptyList()
                    println("✅ Found ${_restaurants.value.size} restaurants matching '$query'")
                } else {
                    _error.value = response.message ?: "Failed to search restaurants"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Network error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Clear error
    fun clearError() {
        _error.value = null
    }
}