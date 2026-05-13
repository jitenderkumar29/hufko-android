package com.example.hufko.api.services.models

data class Banner(
    val id: String,
    val title: String,
    val description: String? = null,
    val shortDescription: String? = null,
    val resourceName: String,
    val priority: Int,
    val bannerType: String,
    val superCategory: String,
    val category: String,
    val subCategory: String? = null,

    val imageUrl: String = "",
    val thumbnailUrl: String = "",
    val mobileImageUrl: String = "",
    val tabletImageUrl: String? = null,

    val clickUrl: String? = null,
    val deepLink: String? = null,
    val tags: List<String> = emptyList(),

    // Diet food specific fields
    val price: String? = null,
    val restaurantName: String? = null,
    val rating: String? = null,
    val deliveryTime: String? = null,
    val distance: String? = null,
    val discount: String? = null,
    val discountAmount: String? = null,
    val address: String? = null,
    val calories: String? = null,
    val protein: String? = null,
    val isHighProtein: Boolean = false
)