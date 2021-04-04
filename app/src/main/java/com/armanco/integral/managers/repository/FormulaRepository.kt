package com.armanco.integral.managers.repository

import com.armanco.integral.managers.db.FormulaDao
import com.armanco.integral.models.Formula
import javax.inject.Inject

class FormulaRepository @Inject constructor(
    private val formulaDao: FormulaDao
){
    suspend fun getAll() = formulaDao.getAll()
    suspend fun getCount() = formulaDao.getCount()
    suspend fun deleteAll() = formulaDao.deleteAll()
    suspend fun getByCategory(categoryId: String) = formulaDao.getByCategory(categoryId)
    suspend fun insertAll(vararg formula: Formula) = formulaDao.insertAll(*formula)
}
