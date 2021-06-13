package com.armanco.integral.data.repository

import com.armanco.integral.R
import com.armanco.integral.data.db.CategoryDao
import com.armanco.integral.data.models.Category
import com.armanco.integral.data.models.Section
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
){
    suspend fun getAll() = categoryDao.getAll()
    suspend fun getBySection(section: Section) = categoryDao.getBySection(section)
    suspend fun getOne(id: Int) = categoryDao.getOne(id)
    suspend fun getCount() = categoryDao.getCount()
    suspend fun deleteAll() = categoryDao.deleteAll()
    suspend fun insertAll(vararg category: Category) = categoryDao.insertAll(*category)
    suspend fun populate() {
        deleteAll()
        insertAll(
            Category(1, R.drawable.ic_cat_1, R.string.category_1, Section.INTEGRAL),
            Category(2, R.drawable.ic_cat_2, R.string.category_2, Section.INTEGRAL),
            Category(3, R.drawable.ic_cat_3, R.string.category_3, Section.INTEGRAL),
            Category(4, R.drawable.ic_cat_4, R.string.category_4, Section.INTEGRAL),
            Category(5, R.drawable.ic_cat_5, R.string.category_5, Section.INTEGRAL),
            Category(6, R.drawable.ic_cat_6, R.string.category_6, Section.INTEGRAL),
            Category(7, R.drawable.ic_cat_7, R.string.category_7, Section.INTEGRAL),
            Category(8, R.drawable.ic_cat_8, R.string.category_8, Section.INTEGRAL),
            Category(9, R.drawable.ic_cat_9, R.string.category_9, Section.INTEGRAL),
            Category(10, R.drawable.ic_cat_10, R.string.category_10, Section.INTEGRAL),
            Category(11, R.drawable.ic_cat_11, R.string.category_11, Section.INTEGRAL),

            Category(12, R.drawable.ic_formula, R.string.category_tri_1, Section.TRIGONOMETRY),
            Category(13, R.drawable.ic_formula, R.string.category_tri_2, Section.TRIGONOMETRY),
            Category(14, R.drawable.ic_formula, R.string.category_tri_3, Section.TRIGONOMETRY),
            Category(15, R.drawable.ic_formula, R.string.category_tri_4, Section.TRIGONOMETRY),
            Category(16, R.drawable.ic_formula, R.string.category_tri_5, Section.TRIGONOMETRY),
            Category(17, R.drawable.ic_formula, R.string.category_tri_6, Section.TRIGONOMETRY),
            Category(18, R.drawable.ic_formula, R.string.category_tri_7, Section.TRIGONOMETRY),
            Category(19, R.drawable.ic_formula, R.string.category_tri_8, Section.TRIGONOMETRY),
            Category(20, R.drawable.ic_formula, R.string.category_tri_9, Section.TRIGONOMETRY),
            Category(21, R.drawable.ic_formula, R.string.category_tri_10, Section.TRIGONOMETRY),
            Category(22, R.drawable.ic_formula, R.string.category_tri_11, Section.TRIGONOMETRY),
            Category(23, R.drawable.ic_formula, R.string.category_tri_12, Section.TRIGONOMETRY),
        )
    }

}
