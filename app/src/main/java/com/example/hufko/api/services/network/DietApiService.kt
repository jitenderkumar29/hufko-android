package com.example.hufko.api.services.network

import retrofit2.Response
import retrofit2.http.*
import com.example.hufko.api.services.models.DietFoodItem
import com.example.hufko.api.services.models.DietApiResponse
import com.example.hufko.api.services.models.DietFoodRequest

interface DietApiService {
    @POST("api/diet-foods/list")
    suspend fun getDietFoodItems(
        @Body request: DietFoodRequest
    ): Response<DietApiResponse<List<DietFoodItem>>>

    @GET("api/diet-foods/category/{subCategory}")
    suspend fun getDietFoodItemsBySubCategory(
        @Path("subCategory") subCategory: String
    ): Response<DietApiResponse<List<DietFoodItem>>>

    @GET("api/diet-foods/{id}")
    suspend fun getDietFoodItemById(
        @Path("id") id: Int
    ): Response<DietApiResponse<DietFoodItem>>
}