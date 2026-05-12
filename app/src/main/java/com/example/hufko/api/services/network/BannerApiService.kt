package com.example.hufko.api.services.network

import com.example.hufko.api.services.model.Banner
import com.example.hufko.api.services.model.BannerPageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BannerApiService {

    @GET("api/banners")
    suspend fun getAllBanners(): BannerPageResponse

    @GET("api/banners/active")
    suspend fun getActiveBanners(): BannerPageResponse

    @GET("api/banners/home")
    suspend fun getHomeBanners(): BannerPageResponse

    @GET("api/banners/type/{bannerType}")
    suspend fun getBannersByType(
        @Path("bannerType") bannerType: String
    ): BannerPageResponse

    @GET("api/banners/supercategory/{superCategoryId}")
    suspend fun getBannersBySuperCategory(
        @Path("superCategoryId") superCategoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): BannerPageResponse

    @GET("api/banners/category/{categoryId}")
    suspend fun getBannersByCategory(
        @Path("categoryId") categoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): BannerPageResponse

    @GET("api/banners/supercategory/{superCategoryId}/category/{categoryId}")
    suspend fun getBannersBySuperCategoryAndCategory(
        @Path("superCategoryId") superCategoryId: String,
        @Path("categoryId") categoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): BannerPageResponse

    @GET("api/banners/subcategory/{subCategoryId}")
    suspend fun getBannersBySubCategory(
        @Path("subCategoryId") subCategoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): BannerPageResponse

    @GET("api/banners/search")
    suspend fun searchBanners(
        @Query("q") query: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): BannerPageResponse

    @GET("api/banners/{bannerId}")
    suspend fun getBannerById(
        @Path("bannerId") bannerId: String
    ): Banner
}