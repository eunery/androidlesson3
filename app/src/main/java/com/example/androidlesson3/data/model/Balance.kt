package com.example.androidlesson3.data.model

import com.google.gson.annotations.SerializedName

data class Balance(
    @SerializedName("id")
    val id: Int,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("toPay")
    val toPay: Double
)
