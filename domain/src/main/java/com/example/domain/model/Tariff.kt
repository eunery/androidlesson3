package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class Tariff(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("amount")
    val amount: Double
)
