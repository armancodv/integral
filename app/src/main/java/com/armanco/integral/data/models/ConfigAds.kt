package com.armanco.integral.data.models

data class ConfigAds (
    val bannerId: String? = null,
    val interstitialId: String? = null,
    val randomSize: Int? = null,
    val integral: ConfigAdsApp? = null,
    val integralPersian: ConfigAdsApp? = null,
    val trigonometry: ConfigAdsApp? = null,
    val trigonometryPersian: ConfigAdsApp? = null,
) {
    companion object {
        const val KEY = "ads"
    }
}