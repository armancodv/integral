package com.armanco.integral.managers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.armanco.integral.models.Category
import com.armanco.integral.models.Item

@Database(entities = [Category::class, Item::class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun itemDao(): ItemDao
}