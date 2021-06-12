package com.armanco.integral.data.repository

import com.armanco.integral.data.datastore.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class StatRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) {
    suspend fun setXp(value: Int?) {
        dataStoreManager.setXp(value)
    }

    val xp: Flow<Int?> = dataStoreManager.xp

    suspend fun increaseXp(increase: Int = 1): Int {
        var value = xp.first() ?: 0
        value += increase
        setXp(value)
        return value
    }

    suspend fun setCounterPlot(value: Int?) {
        dataStoreManager.setCounterPlot(value)
    }

    val counterPlot: Flow<Int?> = dataStoreManager.counterPlot

    suspend fun increaseCounterPlot(): Int {
        var value = counterPlot.first() ?: 0
        value ++
        setCounterPlot(value)
        return value
    }

    suspend fun setCounterCalculate(value: Int?) {
        dataStoreManager.setCounterCalculate(value)
    }

    val counterCalculate: Flow<Int?> = dataStoreManager.counterCalculate

    suspend fun increaseCounterCalculate(): Int {
        var value = counterCalculate.first() ?: 0
        value ++
        setCounterCalculate(value)
        return value
    }

    suspend fun setCounterFormula(value: Int?) {
        dataStoreManager.setCounterFormulaCounter(value)
    }

    val counterFormula: Flow<Int?> = dataStoreManager.counterFormula

    suspend fun increaseCounterFormula(): Int {
        var value = counterFormula.first() ?: 0
        value ++
        setCounterFormula(value)
        return value
    }

    suspend fun setCounterAd(value: Int?) {
        dataStoreManager.setCounterAd(value)
    }

    val counterAd: Flow<Int?> = dataStoreManager.counterAd

    suspend fun increaseCounterAd(): Int {
        var value = counterAd.first() ?: 0
        value ++
        setCounterAd(value)
        return value
    }

    companion object {
        const val XP_CALCULATE = 3
        const val XP_PLOT = 3
        const val XP_FORMULA = 2
        const val XP_AD = 5
    }

}