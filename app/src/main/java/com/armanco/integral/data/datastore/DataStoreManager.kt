package com.armanco.integral.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.armanco.integral.AppConstants.ENCRYPT_PASSWORD
import com.armanco.integral.utils.extensions.dataStore
import com.scottyab.aescrypt.AESCrypt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class DataStoreManager(appContext: Context) {

    private val dataStore = appContext.dataStore

    suspend fun setXp(value: Int?) {
        setEncrypt(XP, value)
    }

    val xp: Flow<Int?> = getDecryptInt(XP)

    suspend fun setCounterPlot(value: Int?) {
        setEncrypt(COUNTER_PLOT, value)
    }

    val counterPlot: Flow<Int?> = getDecryptInt(COUNTER_PLOT)

    suspend fun setCounterCalculate(value: Int?) {
        setEncrypt(COUNTER_CALCULATE, value)
    }

    val counterCalculate: Flow<Int?> = getDecryptInt(COUNTER_CALCULATE)

    suspend fun setCounterFormulaCounter(value: Int?) {
        setEncrypt(COUNTER_FORMULA, value)
    }

    val counterFormula: Flow<Int?> = getDecryptInt(COUNTER_FORMULA)

    suspend fun setCounterAd(value: Int?) {
        setEncrypt(COUNTER_AD, value)
    }

    val counterAd: Flow<Int?> = getDecryptInt(COUNTER_AD)

    private suspend fun setEncrypt(key: String, value: Any?) {
        try {
            dataStore.edit { preferences ->
                if (value != null && value != "") {
                    preferences[stringPreferencesKey(key)] =
                        AESCrypt.encrypt(ENCRYPT_PASSWORD, value.toString())
                } else {
                    preferences.remove(stringPreferencesKey(key))
                }
            }
        } catch (ignore: Exception) {
        }
    }

    private fun getDecryptInt(key: String): Flow<Int?> {
        return dataStore.data.map { preferences ->
            if (!preferences[stringPreferencesKey(key)].isNullOrBlank()) {
                AESCrypt.decrypt(ENCRYPT_PASSWORD, preferences[stringPreferencesKey(key)])
                    .toIntOrNull()
            } else null
        }
    }

    companion object {
        const val XP = "xp"
        const val COUNTER_PLOT = "counterPlot"
        const val COUNTER_CALCULATE = "counterCalculate"
        const val COUNTER_FORMULA = "counterFormula"
        const val COUNTER_AD = "counterAd"
    }
}