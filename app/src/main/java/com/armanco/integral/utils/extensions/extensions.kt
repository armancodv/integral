package com.armanco.integral.utils.extensions

import com.armanco.integral.AppConstants
import com.armanco.integral.BuildConfig

val isTrigonometryOrTrigonometryPersian: Boolean
    get() {
        return BuildConfig.FLAVOR == AppConstants.FLAVOR_TRIGONOMETRY || BuildConfig.FLAVOR == AppConstants.FLAVOR_TRIGONOMETRY_PERSIAN
    }

val isPro: Boolean
    get() {
        return BuildConfig.FLAVOR == AppConstants.FLAVOR_PRO
    }

val isIntegralPersianOrTrigonometryPersian: Boolean
    get() {
        return BuildConfig.FLAVOR == AppConstants.FLAVOR_PERSIAN || BuildConfig.FLAVOR == AppConstants.FLAVOR_TRIGONOMETRY_PERSIAN
    }