package com.armanco.integral.managers.db

import androidx.room.*
import com.armanco.integral.models.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    suspend fun getAll(): List<Item>

    @Query("SELECT * FROM item WHERE category_id = :categoryId")
    suspend fun getByCategory(categoryId: String): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("DELETE FROM item")
    suspend fun deleteAll()

    @Query("SELECT COUNT(id) FROM item")
    suspend fun getCount(): Int
}