package com.armanco.integral.models

data class ConfigAds (
    val bannerId: String? = null,
    val interstitialId: String? = null
) {
    companion object {
        const val KEY = "ads"
    }
}