package com.armanco.integral.utils.extensions

import com.armanco.integral.AppConstants
import com.armanco.integral.BuildConfig

val isPro: Boolean
    get() {
        return BuildConfig.FLAVOR == AppConstants.FLAVOR_PRO
    }

val isPersian: Boolean
    get() {
        return BuildConfig.FLAVOR == AppConstants.FLAVOR_PERSIAN
    }