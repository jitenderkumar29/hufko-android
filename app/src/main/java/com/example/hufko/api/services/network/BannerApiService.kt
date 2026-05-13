package com.example.hufko.api.services.network

import com.example.hufko.api.services.models.Banner
import com.example.hufko.api.services.models.BannerPageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

interface BannerApiService {

    @GET("api/banners")
    suspend fun getAllBanners(): Response<BannerPageResponse>

    @GET("api/banners/active")
    suspend fun getActiveBanners(): Response<BannerPageResponse>

    @GET("api/banners/home")
    suspend fun getHomeBanners(): Response<BannerPageResponse>

    @GET("api/banners/type/{bannerType}")
    suspend fun getBannersByType(
        @Path("bannerType") bannerType: String
    ): Response<BannerPageResponse>

    @GET("api/banners/supercategory/{superCategoryId}")
    suspend fun getBannersBySuperCategory(
        @Path("superCategoryId") superCategoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<BannerPageResponse>

    @GET("api/banners/category/{categoryId}")
    suspend fun getBannersByCategory(
        @Path("categoryId") categoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<BannerPageResponse>

    @GET("api/banners/supercategory/{superCategoryId}/category/{categoryId}")
    suspend fun getBannersBySuperCategoryAndCategory(
        @Path("superCategoryId") superCategoryId: String,
        @Path("categoryId") categoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<BannerPageResponse>

    @GET("api/banners/supercategory/{superCategoryId}/category/{categoryId}/subcategory/{subCategoryId}")
    suspend fun getBannersBySuperCategoryAndCategoryAndSubCategory(
        @Path("superCategoryId") superCategoryId: String,
        @Path("categoryId") categoryId: String,
        @Path("subCategoryId") subCategoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<BannerPageResponse>

    @GET("api/banners/subcategory/{subCategoryId}")
    suspend fun getBannersBySubCategory(
        @Path("subCategoryId") subCategoryId: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<BannerPageResponse>

    @GET("api/banners/search")
    suspend fun searchBanners(
        @Query("q") query: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<BannerPageResponse>

    @GET("api/banners/{bannerId}")
    suspend fun getBannerById(
        @Path("bannerId") bannerId: String
    ): Response<Banner>
}