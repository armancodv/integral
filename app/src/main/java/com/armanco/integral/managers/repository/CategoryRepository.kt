package com.armanco.integral.managers.repository

import com.armanco.integral.managers.db.CategoryDao
import com.armanco.integral.models.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
){
    suspend fun getAll() = categoryDao.getAll()
    suspend fun insertAll(vararg category: Category) = categoryDao.insertAll(*category)
}
