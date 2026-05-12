package com.example.hufko.api.services.model

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("bannerId")
    val bannerId: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("shortDescription")
    val shortDescription: String? = null,

    @SerializedName("imageUrl")
    val imageUrl: String? = null,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String? = null,

    @SerializedName("mobileImageUrl")
    val mobileImageUrl: String? = null,

    @SerializedName("tabletImageUrl")
    val tabletImageUrl: String? = null,

    @SerializedName("clickUrl")
    val clickUrl: String? = null,

    @SerializedName("deepLink")
    val deepLink: String? = null,

    @SerializedName("bannerType")
    val bannerType: String? = null,

    @SerializedName("priority")
    val priority: Int? = 0,

    @SerializedName("isActive")
    val isActive: Boolean? = true,

    @SerializedName("superCategory")
    val superCategory: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("subCategory")
    val subCategory: String? = null,

    @SerializedName("resourceName")
    val resourceName: String? = null,

    @SerializedName("tags")
    val tags: List<String>? = emptyList(),

    @SerializedName("viewCount")
    val viewCount: Int? = 0,

    @SerializedName("clickCount")
    val clickCount: Int? = 0,

    @SerializedName("ctr")
    val ctr: Double? = 0.0
)