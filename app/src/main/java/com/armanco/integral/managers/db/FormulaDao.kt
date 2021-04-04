package com.armanco.integral.managers.db

import androidx.room.*
import com.armanco.integral.models.Formula

@Dao
interface FormulaDao {
    @Query("SELECT * FROM formula")
    suspend fun getAll(): List<Formula>

    @Query("SELECT * FROM formula WHERE category_id = :categoryId")
    suspend fun getByCategory(categoryId: Int): List<Formula>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg formula: Formula)

    @Delete
    suspend fun delete(formula: Formula)

    @Query("DELETE FROM formula")
    suspend fun deleteAll()

    @Query("SELECT COUNT(id) FROM formula")
    suspend fun getCount(): Int
}