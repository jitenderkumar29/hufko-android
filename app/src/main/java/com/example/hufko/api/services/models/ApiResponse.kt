package com.example.hufko.api.services.models

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean = false,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("data")
    val data: T? = null,

    @SerializedName("timestamp")
    val timestamp: String? = null,

    @SerializedName("statusCode")
    val statusCode: Int? = null,

    @SerializedName("error")
    val error: String? = null,

    @SerializedName("errors")
    val errors: List<String>? = null
)