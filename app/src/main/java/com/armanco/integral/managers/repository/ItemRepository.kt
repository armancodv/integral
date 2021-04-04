package com.armanco.integral.managers.repository

import com.armanco.integral.managers.db.ItemDao
import com.armanco.integral.models.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val itemDao: ItemDao
){
    suspend fun getAll() = itemDao.getAll()
    suspend fun getCount() = itemDao.getCount()
    suspend fun getByCategory(categoryId: String) = itemDao.getByCategory(categoryId)
    suspend fun insertAll(vararg item: Item) = itemDao.insertAll(*item)
}
