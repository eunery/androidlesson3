package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "something")
data class SomeEntity (
    @PrimaryKey val id: Int,
    val text: String
)