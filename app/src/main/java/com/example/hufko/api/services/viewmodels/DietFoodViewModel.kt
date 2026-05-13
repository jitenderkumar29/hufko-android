package com.example.hufko.api.services.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.hufko.api.services.config.NetworkConfig
import com.example.hufko.api.services.models.Banner
import com.example.hufko.api.services.models.FoodItemBannerPreNextF
import com.example.hufko.api.services.network.BannerRetrofitClient.apiService
import com.example.hufko.api.services.viewmodels.BannerViewModels
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DietFoodViewModel : BannerViewModels() {

    private val _dietFoodItems = MutableStateFlow<List<Banner>>(emptyList())
    val dietFoodItems: StateFlow<List<Banner>> = _dietFoodItems.asStateFlow()

    fun loadDietBannersBySuperCategoryAndCategory(
        superCategoryId: String,
        categoryId: String,
        page: Int = 0,
        size: Int = 50
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = apiService.getBannersBySuperCategoryAndCategory(
                    superCategoryId,
                    categoryId,
                    page,
                    size
                )
                println("📦 response:-------------------------------------- ${response}")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _dietFoodItems.value = body.content
                        println("✅ Loaded ${body.content.size} diet food banners")

                        // Debug print each banner's diet fields
                        body.content.forEach { banner ->
                            println("📦 Banner: ${banner.title}")
                            println("   Price: '${banner.price}'")
                            println("   Restaurant: '${banner.restaurantName}'")
                            println("   Rating: '${banner.rating}'")
                            println("   Calories: '${banner.calories}'")
                            println("   Protein: '${banner.protein}'")
                        }
                    } else {
                        _error.value = "Empty response body"
                    }
                } else {
                    _error.value = "HTTP ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun convertToBannerItems(banners: List<Banner>): List<FoodItemBannerPreNextF> {
        return banners.map { banner ->
            FoodItemBannerPreNextF(
                id = banner.id?.toIntOrNull() ?: 0,
                imageRes = null,
                imageUrl = getFullImageUrl(banner.imageUrl),
                title = banner.title,
                price = banner.price ?: "0",
                restaurantName = banner.restaurantName ?: "",
                rating = banner.rating ?: "0",
                deliveryTime = banner.deliveryTime ?: "",
                distance = banner.distance ?: "",
                discount = banner.discount ?: "",
                discountAmount = banner.discountAmount ?: "",
                address = banner.address ?: "",
                calories = banner.calories ?: "0",
                protein = banner.protein ?: "0",
                isHighProtein = banner.isHighProtein
            )
        }
    }

    fun convertToDynamicBannerItems(banners: List<Banner>): List<FoodItemBannerPreNextF> {
        return banners.map { banner ->
            FoodItemBannerPreNextF(
                id = banner.id?.toIntOrNull() ?: 0,
                imageRes = null,
                imageUrl = getFullImageUrl(banner.imageUrl),
                title = banner.title,
                price = banner.price ?: "0",
                restaurantName = banner.restaurantName ?: "",
                rating = banner.rating ?: "0",
                deliveryTime = banner.deliveryTime ?: "",
                distance = banner.distance ?: "",
                discount = banner.discount ?: "",
                discountAmount = banner.discountAmount ?: "",
                address = banner.address ?: "",
                calories = banner.calories ?: "0",
                protein = banner.protein ?: "0",
                isHighProtein = banner.isHighProtein
            )
        }
    }

    private fun getFullImageUrl(imagePath: String?): String {
        if (imagePath.isNullOrBlank()) return ""

        return if (imagePath.startsWith("http")) {
            imagePath
        } else {
            val baseUrl = NetworkConfig.BASE_URL.removeSuffix("/")
            if (imagePath.startsWith("/")) {
                "$baseUrl$imagePath"
            } else {
                "$baseUrl/$imagePath"
            }
        }
    }
}