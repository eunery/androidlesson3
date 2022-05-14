package com.example.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("names")
    val names: String,
    @SerializedName("address")
    val address: String
)
