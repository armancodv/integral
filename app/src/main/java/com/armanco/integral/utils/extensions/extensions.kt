package com.armanco.integral.utils.extensions

import com.armanco.integral.BuildConfig

val isPro: Boolean
    get() {
    return when (BuildConfig.FLAVOR) {
        "pro" -> true
        else -> false
    }
}

val isPersian: Boolean
    get() {
    return when (BuildConfig.FLAVOR) {
        "persian" -> true
        else -> false
    }
}