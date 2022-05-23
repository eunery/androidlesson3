package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.model.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun saveAll(vararg users: UserEntity)
    @Query("select * from user")
    suspend fun getAll(): List<UserEntity>
}