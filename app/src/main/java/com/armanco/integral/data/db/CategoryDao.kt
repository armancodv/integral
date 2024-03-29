package com.armanco.integral.data.db

import androidx.room.*
import com.armanco.integral.data.models.Category
import com.armanco.integral.data.models.Section

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    suspend fun getAll(): List<Category>

    @Query("SELECT * FROM category WHERE section = :section")
    suspend fun getBySection(section: Section): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("DELETE FROM category")
    suspend fun deleteAll()

    @Query("SELECT COUNT(id) FROM category")
    suspend fun getCount(): Int

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getOne(id: Int): Category
}