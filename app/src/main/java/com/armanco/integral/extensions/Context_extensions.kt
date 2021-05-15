package com.armanco.integral.extensions

import android.content.Context
import android.os.Build
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
