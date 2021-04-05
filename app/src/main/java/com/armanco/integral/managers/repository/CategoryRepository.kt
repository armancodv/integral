package com.armanco.integral.managers.repository

import com.armanco.integral.R
import com.armanco.integral.managers.db.CategoryDao
import com.armanco.integral.models.Category
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
){
    suspend fun getAll() = categoryDao.getAll()
    suspend fun getOne(id: Int) = categoryDao.getOne(id)
    suspend fun getCount() = categoryDao.getCount()
    suspend fun deleteAll() = categoryDao.deleteAll()
    suspend fun insertAll(vararg category: Category) = categoryDao.insertAll(*category)
    suspend fun populate() {
        deleteAll()
        insertAll(
            Category(1, R.drawable.ic_cat_1, R.string.category_1),
            Category(2, R.drawable.ic_cat_2, R.string.category_2),
            Category(3, R.drawable.ic_cat_3, R.string.category_3),
            Category(4, R.drawable.ic_cat_4, R.string.category_4),
            Category(5, R.drawable.ic_cat_5, R.string.category_5),
            Category(6, R.drawable.ic_cat_6, R.string.category_6),
            Category(7, R.drawable.ic_cat_7, R.string.category_7),
            Category(8, R.drawable.ic_cat_8, R.string.category_8),
            Category(9, R.drawable.ic_cat_9, R.string.category_9),
            Category(10, R.drawable.ic_cat_10, R.string.category_10),
            Category(11, R.drawable.ic_cat_11, R.string.category_11),
        )
    }

}
