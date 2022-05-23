package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.model.SomeEntity

@Dao
interface SomeDao {
    @Query("Select * from something")
    fun getAll(): List<SomeEntity>

    @Insert
    fun addAll(vararg some: SomeEntity)

    @Delete
    fun delete(someEntity: SomeEntity)
}