package com.example.hufko.api.services.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hufko.api.services.config.NetworkConfig
import com.example.hufko.api.services.models.Banner
import com.example.hufko.api.services.models.BannerPageResponse
import com.example.hufko.api.services.network.BannerRetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

open class BannerViewModels : ViewModel() {

    private val apiService = BannerRetrofitClient.apiService

    private val _healthyScoreBanners = MutableStateFlow<List<Banner>>(emptyList())
    val healthyScoreBanners: StateFlow<List<Banner>> = _healthyScoreBanners.asStateFlow()

    private val _homeBanners = MutableStateFlow<List<Banner>>(emptyList())
    val homeBanners: StateFlow<List<Banner>> = _homeBanners.asStateFlow()

    private val _allBanners = MutableStateFlow<List<Banner>>(emptyList())
    val allBanners: StateFlow<List<Banner>> = _allBanners.asStateFlow()

    private val _bannersByCategory = MutableStateFlow<List<Banner>>(emptyList())
    val bannersByCategory: StateFlow<List<Banner>> = _bannersByCategory.asStateFlow()

    private val _filteredBanners = MutableStateFlow<List<Banner>>(emptyList())
    val filteredBanners: StateFlow<List<Banner>> = _filteredBanners.asStateFlow()

    private val _selectedBanner = MutableStateFlow<Banner?>(null)
    val selectedBanner: StateFlow<Banner?> = _selectedBanner.asStateFlow()

    val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _totalPages = MutableStateFlow(0)
    val totalPages: StateFlow<Int> = _totalPages.asStateFlow()

    private val _totalElements = MutableStateFlow(0L)
    val totalElements: StateFlow<Long> = _totalElements.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    private val _currentSize = MutableStateFlow(20)
    val currentSize: StateFlow<Int> = _currentSize.asStateFlow()

    private val _currentSuperCategory = MutableStateFlow("")
    val currentSuperCategory: StateFlow<String> = _currentSuperCategory.asStateFlow()

    private val _currentCategory = MutableStateFlow("")
    val currentCategory: StateFlow<String> = _currentCategory.asStateFlow()

    fun loadHomeBanners() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response: Response<BannerPageResponse> = apiService.getHomeBanners()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val banners = fixImageUrls(body.content)
                        _homeBanners.value = banners
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

    fun loadAllBanners() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response: Response<BannerPageResponse> = apiService.getAllBanners()

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val banners = fixImageUrls(body.content)
                        _allBanners.value = banners
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

    fun loadBannersByCategory(
        categoryId: String,
        page: Int = 0,
        size: Int = 20
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response: Response<BannerPageResponse> = apiService.getBannersByCategory(
                    categoryId,
                    page,
                    size
                )

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        handlePagedResponse(body)
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

    fun loadBannersBySuperCategory(
        superCategoryId: String,
        page: Int = 0,
        size: Int = 20
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response: Response<BannerPageResponse> = apiService.getBannersBySuperCategory(
                    superCategoryId,
                    page,
                    size
                )

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        handlePagedResponse(body)
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

    fun loadBannersBySuperCategoryAndCategory(
        superCategoryId: String,
        categoryId: String,
        page: Int = 0,
        size: Int = 20
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response: Response<BannerPageResponse> = apiService
                    .getBannersBySuperCategoryAndCategory(
                        superCategoryId,
                        categoryId,
                        page,
                        size
                    )

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        handlePagedResponse(body)
                        _currentSuperCategory.value = superCategoryId
                        _currentCategory.value = categoryId
                    } else {
                        _error.value = "Empty response body"
                        _bannersByCategory.value = emptyList()
                    }
                } else {
                    _error.value = "HTTP ${response.code()}: ${response.message()}"
                    _bannersByCategory.value = emptyList()
                }

            } catch (e: Exception) {
                _error.value = e.message
                _bannersByCategory.value = emptyList()
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

//    fun loadDietBannersBySuperCategoryAndCategory(
//        superCategoryId: String,
//        categoryId: String,
//        page: Int = 0,
//        size: Int = 20
//    ) {
//        viewModelScope.launch {
//            try {
//                _isLoading.value = true
//                _error.value = null
//
//                val response: Response<BannerPageResponse> = apiService
//                    .getBannersBySuperCategoryAndCategory(
//                        superCategoryId,
//                        categoryId,
//                        page,
//                        size
//                    )
//
//                if (response.isSuccessful) {
//                    val body = response.body()
//                    if (body != null) {
//                        handlePagedResponse(body)
//                        _currentSuperCategory.value = superCategoryId
//                        _currentCategory.value = categoryId
//                    } else {
//                        _error.value = "Empty response body"
//                        _bannersByCategory.value = emptyList()
//                    }
//                } else {
//                    _error.value = "HTTP ${response.code()}: ${response.message()}"
//                    _bannersByCategory.value = emptyList()
//                }
//
//            } catch (e: Exception) {
//                _error.value = e.message
//                _bannersByCategory.value = emptyList()
//                e.printStackTrace()
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }

    fun loadHealthyScoreBanners() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response: Response<BannerPageResponse> = apiService
                    .getBannersBySuperCategoryAndCategory(
                        superCategoryId = "FOOD_SUPER",
                        categoryId = "ALL_FOOD_HEALTHY_SCORE",
                        page = 0,
                        size = 20
                    )

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _healthyScoreBanners.value = fixImageUrls(body.content)
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

    fun loadBannerById(bannerId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response: Response<Banner> = apiService.getBannerById(bannerId)

                if (response.isSuccessful) {
                    val banner = response.body()
                    if (banner != null) {
                        _selectedBanner.value = banner.copy(
                            imageUrl = fixSingleImageUrl(banner.imageUrl)
                        )
                    } else {
                        _error.value = "Banner not found"
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

    private fun handlePagedResponse(response: BannerPageResponse) {
        val fixedBanners = fixImageUrls(response.content)
        _bannersByCategory.value = fixedBanners
        _filteredBanners.value = fixedBanners
        _totalPages.value = response.totalPages
        _totalElements.value = response.totalElements
        _currentPage.value = response.number
        _currentSize.value = response.size
    }

    private fun fixImageUrls(banners: List<Banner>): List<Banner> {
        return banners.map { banner ->
            banner.copy(
                imageUrl = fixSingleImageUrl(banner.imageUrl)
            )
        }
    }

    private fun fixSingleImageUrl(url: String?): String {
        if (url.isNullOrBlank()) return ""

        val baseUrl = NetworkConfig.BASE_URL.removeSuffix("/")

        return when {
            url.startsWith("http") -> url
            url.startsWith("/") -> "$baseUrl$url"
            else -> "$baseUrl/$url"
        }
    }

    fun getValidBannersWithImages(): List<Banner> {
        return _bannersByCategory.value.filter {
            it.imageUrl.isNotBlank()
        }
    }

    fun hasValidBanners(): Boolean {
        return getValidBannersWithImages().isNotEmpty()
    }

    fun clearError() {
        _error.value = null
    }

    fun clearBanners() {
        _bannersByCategory.value = emptyList()
        _filteredBanners.value = emptyList()
    }
}