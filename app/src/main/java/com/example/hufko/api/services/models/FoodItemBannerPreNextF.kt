package com.example.hufko.api.services.models

import androidx.annotation.DrawableRes

data class FoodItemBannerPreNextF(
    val id: Int,
    @DrawableRes val imageRes: Int? = null,  // Made optional
    val imageUrl: String? = null,  // Added for network images
    val title: String,
    val price: String,
    val restaurantName: String,
    val rating: String,
    val deliveryTime: String,
    val distance: String,
    val discount: String,
    val discountAmount: String,
    val address: String,
    val calories: String,
    val protein: String,
    val isHighProtein: Boolean
)