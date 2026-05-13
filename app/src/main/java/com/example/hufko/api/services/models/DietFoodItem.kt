package com.example.hufko.api.services.models

// DietFoodItem.kt
data class DietFoodItem(
    val id: Int,
    val title: String,
    val price: String,
    val restaurantName: String,
    val rating: String,
    val deliveryTime: String,
    val distance: String,
    val discount: String? = null,
    val discountAmount: String? = null,
    val address: String,
    val calories: String,
    val protein: String,
    val isHighProtein: Boolean = false,
    val imageUrl: String,
    val category: String,
    val subCategory: String? = null
)

// API Response Wrapper - Renamed to avoid conflict with other ApiResponse classes
data class DietApiResponse<T>(
    val success: Boolean,
    val data: T,
    val message: String? = null
)

// Request to get diet food items by category
data class DietFoodRequest(
    val superCategory: String = "FOOD_SUPER",
    val category: String = "DIET_FOOD_CAT",
    val subCategory: String? = null
)