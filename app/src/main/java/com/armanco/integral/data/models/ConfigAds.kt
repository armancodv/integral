package com.armanco.integral.data.models

data class ConfigAds (
    val bannerId: String? = null,
    val interstitialId: String? = null,
    val integral: ConfigAdsApp? = null,
    val integralPersian: ConfigAdsApp? = null
) {
    companion object {
        const val KEY = "ads"
    }
}