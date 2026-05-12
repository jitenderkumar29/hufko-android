package com.example.hufko.api.services.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hufko.api.services.config.NetworkConfig
import com.example.hufko.api.services.model.Banner
import com.example.hufko.api.services.network.BannerRetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BannerViewModels : ViewModel() {

    private val apiService = BannerRetrofitClient.apiService

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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
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

    private val _currentBannerType = MutableStateFlow("")
    val currentBannerType: StateFlow<String> = _currentBannerType.asStateFlow()

    fun loadHomeBanners() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                println("🔄 Loading home banners...")
                val response = apiService.getHomeBanners()

                val banners = response.content ?: emptyList()
                val fixedBanners = fixImageUrls(banners)

                _homeBanners.value = fixedBanners
                _error.value = null
                println("✅ Loaded ${fixedBanners.size} home banners")
            } catch (e: Exception) {
                _error.value = e.message
                println("❌ Exception loading home banners: ${e.message}")
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadAllBanners() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                println("🔄 Loading all banners...")
                val response = apiService.getAllBanners()

                val banners = response.content ?: emptyList()
                val fixedBanners = fixImageUrls(banners)

                _allBanners.value = fixedBanners
                _error.value = null
                println("✅ Loaded ${fixedBanners.size} total banners")
            } catch (e: Exception) {
                _error.value = e.message
                println("❌ Exception loading all banners: ${e.message}")
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
            _isLoading.value = true
            _error.value = null

            try {
                println("🔄 Loading banners for category: $categoryId, page: $page, size: $size")
                val response = apiService.getBannersByCategory(categoryId, page, size)

                val banners = response.content ?: emptyList()
                val fixedBanners = fixImageUrls(banners)

                _bannersByCategory.value = fixedBanners
                _filteredBanners.value = fixedBanners
                _totalPages.value = response.totalPages ?: 0
                _totalElements.value = response.totalElements ?: 0L
                _currentPage.value = response.page ?: page
                _currentSize.value = response.size ?: size

                _error.value = null
                println("✅ Loaded ${fixedBanners.size} banners for category $categoryId")
            } catch (e: Exception) {
                _error.value = e.message
                println("❌ Exception loading banners: ${e.message}")
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
            _isLoading.value = true
            _error.value = null

            try {
                println("🔄 Loading banners for super category: $superCategoryId, page: $page, size: $size")
                val response = apiService.getBannersBySuperCategory(superCategoryId, page, size)

                val banners = response.content ?: emptyList()
                val fixedBanners = fixImageUrls(banners)

                _bannersByCategory.value = fixedBanners
                _filteredBanners.value = fixedBanners
                _totalPages.value = response.totalPages ?: 0
                _totalElements.value = response.totalElements ?: 0L
                _currentPage.value = response.page ?: page
                _currentSize.value = response.size ?: size

                _error.value = null
                println("✅ Loaded ${fixedBanners.size} banners for super category $superCategoryId")
            } catch (e: Exception) {
                _error.value = e.message
                println("❌ Exception loading banners: ${e.message}")
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadBannerById(bannerId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                println("🔄 Loading banner by ID: $bannerId")
                val banner = apiService.getBannerById(bannerId)

                // Fix image URL for single banner
                val fixedBanner = banner.copy(
                    imageUrl = banner.imageUrl?.let { url ->
                        if (url.startsWith("/")) {
                            "${NetworkConfig.BASE_URL.dropLast(1)}$url"
                        } else {
                            url
                        }
                    }
                )

                _selectedBanner.value = fixedBanner
                println("✅ Loaded banner: ${fixedBanner.title}")
            } catch (e: Exception) {
                _error.value = e.message
                println("❌ Exception loading banner: ${e.message}")
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
            _isLoading.value = true
            _error.value = null

            try {
                println("🔄 Loading banners for superCategory: $superCategoryId, category: $categoryId")

                val response = apiService.getBannersBySuperCategoryAndCategory(
                    superCategoryId,
                    categoryId,
                    page,
                    size
                )

                println("📡 Response received")
                println("📡 Total Elements: ${response.totalElements}")
                println("📡 Total Pages: ${response.totalPages}")
                println("📡 Current Page: ${response.page}")

                val banners = response.content ?: emptyList()
                println("✅ Loaded ${banners.size} banners from API")

                // Fix image URLs - add base URL if needed
                val fixedBanners = fixImageUrls(banners)

                _bannersByCategory.value = fixedBanners
                _filteredBanners.value = fixedBanners
                _totalPages.value = response.totalPages ?: 0
                _totalElements.value = response.totalElements ?: 0L
                _currentPage.value = response.page ?: page
                _currentSize.value = response.size ?: size
                _currentSuperCategory.value = superCategoryId
                _currentCategory.value = categoryId

                _error.value = null

                // Print first banner for debugging
                fixedBanners.firstOrNull()?.let {
                    println("📸 First banner image URL: ${it.imageUrl}")
                }

            } catch (e: Exception) {
                println("❌ Exception caught: ${e.message}")
                e.printStackTrace()
                _error.value = "Error: ${e.message}"
                _bannersByCategory.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Helper function to fix image URLs
    private fun fixImageUrls(banners: List<Banner>): List<Banner> {
        val baseUrl = NetworkConfig.BASE_URL  // This is without trailing slash
        return banners.map { banner ->
            banner.copy(
                imageUrl = banner.imageUrl?.let { url ->
                    when {
                        url.startsWith("http") -> url  // Already full URL
                        url.startsWith("/") -> "$baseUrl$url"  // Relative path
                        else -> "$baseUrl/$url"  // Add missing slash
                    }
                }
            )
        }
    }

    // Helper function to get valid banners with images
    fun getValidBannersWithImages(): List<Banner> {
        return _bannersByCategory.value.filter { banner ->
            !banner.imageUrl.isNullOrBlank() && banner.isActive == true
        }
    }

    // Helper function to check if there are any valid banners
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