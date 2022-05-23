package com.example.data

import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.data.dao.BalanceDao
import com.example.data.dao.TariffDao
import com.example.data.dao.UserDao
import com.example.data.model.BalanceEntity
import com.example.data.model.TariffEntity
import com.example.data.model.UserEntity

@Database(entities = [
    TariffEntity::class,
    BalanceEntity::class,
    UserEntity::class,
], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getTariffDao(): TariffDao
    abstract fun getBalanceDao(): BalanceDao

}