// com/example/hufko/api/services/viewmodels/RestaurantViewModel.kt
package com.example.hufko.api.services.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hufko.api.services.models.RestaurantItemFull
import com.example.hufko.api.services.network.RestaurantApiService
import com.example.hufko.api.services.network.RetrofitClient
import com.example.hufko.api.services.repository.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantViewModel(
    private val repository: RestaurantRepository = RestaurantRepository(
        RetrofitClient.instance.create(RestaurantApiService::class.java)
    ),
    private val savedStateHandle: SavedStateHandle = SavedStateHandle()
) : ViewModel() {

    // Existing state
    private val _restaurants = MutableStateFlow<List<RestaurantItemFull>>(emptyList())
    val restaurants: StateFlow<List<RestaurantItemFull>> = _restaurants.asStateFlow()

    // New states for featured and recommended
    private val _featuredRestaurants = MutableStateFlow<List<RestaurantItemFull>>(emptyList())
    val featuredRestaurants: StateFlow<List<RestaurantItemFull>> = _featuredRestaurants.asStateFlow()

    private val _recommendedRestaurants = MutableStateFlow<List<RestaurantItemFull>>(emptyList())
    val recommendedRestaurants: StateFlow<List<RestaurantItemFull>> = _recommendedRestaurants.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Load featured restaurants
    fun loadFeaturedRestaurants(category: String, featured: Boolean = true) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val restaurants = repository.getFeaturedRestaurants(category, featured)
                _featuredRestaurants.value = restaurants
                println("✅ Loaded ${restaurants.size} featured restaurants")
            } catch (e: Exception) {
                _error.value = e.message
                println("❌ Error loading featured restaurants: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Load recommended restaurants
    fun loadRecommendedRestaurants(category: String, recommended: Boolean = true) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val restaurants = repository.getRecommendedRestaurants(category, recommended)
                _recommendedRestaurants.value = restaurants
                println("✅ Loaded ${restaurants.size} recommended restaurants")
            } catch (e: Exception) {
                _error.value = e.message
                println("❌ Error loading recommended restaurants: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Optional: Filter restaurants by featured/recommended from existing list
    fun filterFeaturedRestaurants(restaurants: List<RestaurantItemFull>): List<RestaurantItemFull> {
        return restaurants.filter { it.featured }
    }

    fun filterRecommendedRestaurants(restaurants: List<RestaurantItemFull>): List<RestaurantItemFull> {
        return restaurants.filter { it.recommended }
    }

    fun clearError() {
        _error.value = null
    }

    // Add a factory for creating the ViewModel
    companion object {
        fun provideFactory(
            repository: RestaurantRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RestaurantViewModel(
                    repository,
                    SavedStateHandle()  // Create a new SavedStateHandle
                ) as T
            }
        }
    }
}