package com.example.hufko.api.services.model

import com.google.gson.annotations.SerializedName

data class BannerPageResponse(
    @SerializedName("content")
    val content: List<Banner>? = emptyList(),

    @SerializedName("page")
    val page: Int? = 0,

    @SerializedName("size")
    val size: Int? = 20,

    @SerializedName("totalElements")
    val totalElements: Long? = 0,

    @SerializedName("totalPages")
    val totalPages: Int? = 0
)