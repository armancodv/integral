package com.armanco.integral.extensions

import com.armanco.integral.BuildConfig

val isPro: Boolean
    get() {
    return when (BuildConfig.FLAVOR) {
        "pro" -> true
        else -> false
    }
}