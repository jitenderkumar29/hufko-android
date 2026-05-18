// com/example/hufko/api/services/models/RestaurantModels.kt
package com.example.hufko.api.services.models

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(
    val success: Boolean,
    val data: List<RestaurantItemFull>? = null,
    val count: Int? = null,
    val message: String? = null
)

data class RestaurantItemFull(
    @SerializedName("id")
    val id: String, // Changed from Int to String

    val category: List<String> = listOf("ALL"),
    @SerializedName("categoryRest")
    val categoryRest: String = "",
    val outlet: String = "",
    val title: String = "",
    @SerializedName("restaurantName")
    val restaurantName: String = "",
    val address: Address? = null,
    val description: String = "",
    @SerializedName("imageUrl")
    val imageUrl: String = "",
    @SerializedName("thumbnailImageRes")
    val thumbnailImageRes: String = "",
    @SerializedName("TopRatedImageRes")
    val topRatedImageRes: String = "",
    val videoUrls: List<String> = emptyList(),
    val galleryImages: List<Int> = emptyList(),
    @SerializedName("priceAvg")
    val priceAvg: String = "",
    @SerializedName("discountAvg")
    val discountAvg: String? = null,
    @SerializedName("discountAmountAvg")
    val discountAmountAvg: String? = null,
    @SerializedName("originalPriceAvg")
    val originalPriceAvg: String? = null,
    @SerializedName("minOrderValue")
    val minOrderValue: String = "₹149",
    @SerializedName("deliveryFee")
    val deliveryFee: String = "₹0",
    val rating: String = "",
    @SerializedName("totalRatings")
    val totalRatings: Int = 0,
    @SerializedName("ratingDescription")
    val ratingDescription: String? = null,
    @SerializedName("deliveryTime")
    val deliveryTime: String = "",
    val distance: String = "",
    val premium: String = "standard",
    @SerializedName("acceptingOrders")
    val acceptingOrders: Boolean = true,
    @SerializedName("acceptingOrdersMsg")
    val acceptingOrdersMsg: String = "",
    @SerializedName("isOpen")
    val isOpen: Boolean = true,
    @SerializedName("nextOpenTime")
    val nextOpenTime: String = "",
    @SerializedName("isCurrentlyAcceptingOrders")
    val isCurrentlyAcceptingOrders: Boolean = true,
    @SerializedName("cuisineType")
    val cuisineType: List<String> = emptyList(),
    @SerializedName("isPureVeg")
    val isPureVeg: Boolean = false,
    @SerializedName("hasAlcohol")
    val hasAlcohol: Boolean = false,
    @SerializedName("contactPhone")
    val contactPhone: String = "",
    @SerializedName("contactEmail")
    val contactEmail: String = "",
    val website: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val landmark: String = "",
    @SerializedName("isFavorite")
    val isFavorite: Boolean = false,
    @SerializedName("isSponsored")
    val isSponsored: Boolean = false,
    @SerializedName("isBookmarked")
    val isBookmarked: Boolean = false,
    @SerializedName("offerLabel")
    val offerLabel: String = "",
    @SerializedName("couponCode")
    val couponCode: String = "",
    @SerializedName("freeDelivery")
    val freeDelivery: Boolean = true,
    @SerializedName("hasParking")
    val hasParking: Boolean = false,
    @SerializedName("hasWifi")
    val hasWifi: Boolean = false,
    @SerializedName("hasOutdoorSeating")
    val hasOutdoorSeating: Boolean = false,
    @SerializedName("hasHomeDelivery")
    val hasHomeDelivery: Boolean = true,
    @SerializedName("hasDineIn")
    val hasDineIn: Boolean = false,
    @SerializedName("hasTakeaway")
    val hasTakeaway: Boolean = true,
    @SerializedName("operatingHours")
    val operatingHours: List<OperatingHour> = emptyList(),
    @SerializedName("topRated")
    val topRated: Boolean = false,
    val calories: String = "",
    val protein: String = "",
    @SerializedName("isHighProtein")
    val isHighProtein: Boolean = false,
    @SerializedName("isVeg")
    val isVeg: Boolean = false,
    @SerializedName("isWishlisted")
    val isWishlisted: Boolean = false,
    @SerializedName("highlyReordered")
    val highlyReordered: String = "",
    @SerializedName("reorderedQuantity")
    val reorderedQuantity: String = "",
    @SerializedName("filtersJson")
    val filtersJson: Map<String, Any>? = null,
    val offer: Map<String, Any>? = null,
    @SerializedName("isActive")
    val isActive: Boolean = true,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class Address(
    @SerializedName("houseNo")
    val houseNo: String = "",
    val apartment: String = "",
    val street: String = "",
    val landmark: String = "",
    val city: String = "",
    val district: String = "",
    val state: String = "",
    val country: String = "India",
    @SerializedName("pinCode")
    val pinCode: String = "",
    @SerializedName("addressType")
    val addressType: String = "Commercial"
)

data class OperatingHour(
    val day: String,
    @SerializedName("openTime")
    val openTime: String,
    @SerializedName("closeTime")
    val closeTime: String
)