package com.armanco.integral.managers.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.armanco.integral.models.Category
import com.armanco.integral.models.Formula

@Database(entities = [Category::class, Formula::class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun formulaDao(): FormulaDao
}