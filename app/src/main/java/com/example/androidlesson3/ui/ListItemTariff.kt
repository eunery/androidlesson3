package com.example.androidlesson3.ui


data class ListItemTariff (
    val id: Int,
    val name: String,
    val description: String,
    val amount: Double,
    val onClick: () -> Unit,
)