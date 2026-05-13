package com.example.hufko.api.services.models

import com.google.gson.annotations.SerializedName

data class BannerPageResponse(
    @SerializedName("content", alternate = ["data", "items", "banners"])
    val content: List<Banner>,

    @SerializedName("totalPages")
    val totalPages: Int,

    @SerializedName("totalElements")
    val totalElements: Long,

    @SerializedName("number", alternate = ["pageNumber", "page"])
    val number: Int,

    @SerializedName("size")
    val size: Int,

    @SerializedName("first")
    val first: Boolean,

    @SerializedName("last")
    val last: Boolean,

    @SerializedName("empty")
    val empty: Boolean,

    @SerializedName("sort")
    val sort: Sort? = null,

    @SerializedName("pageable")
    val pageable: Pageable? = null,

    @SerializedName("numberOfElements")
    val numberOfElements: Int = 0
)

data class Pageable(
    @SerializedName("pageNumber")
    val pageNumber: Int,

    @SerializedName("pageSize")
    val pageSize: Int,

    @SerializedName("sort")
    val sort: Sort? = null,

    @SerializedName("offset")
    val offset: Long,

    @SerializedName("paged")
    val paged: Boolean,

    @SerializedName("unpaged")
    val unpaged: Boolean
)

data class Sort(
    @SerializedName("sorted")
    val sorted: Boolean,

    @SerializedName("unsorted")
    val unsorted: Boolean,

    @SerializedName("empty")
    val empty: Boolean
)