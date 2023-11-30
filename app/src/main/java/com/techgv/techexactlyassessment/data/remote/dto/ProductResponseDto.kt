package com.techgv.techexactlyassessment.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ProductResponseDto(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)