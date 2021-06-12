package com.armanco.integral.utils.extensions

import android.content.Context
import android.os.Build
import androidx.datastore.preferences.preferencesDataStore
import com.armanco.integral.AppConstants.DATASTORE
import java.util.*

fun Context.setLocale(locale: Locale) {
    val configuration = resources.configuration
    configuration.setLocale(locale)
    Locale.setDefault(locale)
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
        createConfigurationContext(configuration)
    }
    resources.updateConfiguration(configuration, resources.displayMetrics)
}

val Context.currentLocale: Locale
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            resources.configuration.locales[0]
        } else {
            resources.configuration.locale
        }
    }

val Context.currentLanguage: String
    get() {
        return currentLocale.language
    }

val Context.currentCountry: String
    get() {
        return currentLocale.country
    }

val Context.dataStore by preferencesDataStore(DATASTORE)
