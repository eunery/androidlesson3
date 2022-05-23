package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tariff")
data class TariffEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val amount: Double
)
