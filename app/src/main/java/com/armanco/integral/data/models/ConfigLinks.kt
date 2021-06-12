package com.armanco.integral.data.models

data class ConfigLinks (
    val proPackageName: String? = null,
    val reportBug: String? = null,
    val contribute: String? = null,
    val privacy: String? = null,
    val terms: String? = null,
) {
    companion object {
        const val KEY = "links"
        const val PRO_PACKAGE_NAME_DEFAULT = "com.armanco.integral_pro"
        const val REPORT_BUG_DEFAULT = "https://github.com/armancodv/integral/issues"
        const val CONTRIBUTE_DEFAULT = "https://github.com/armancodv/integral"
        const val PRIVACY_DEFAULT = "https://github.com/armancodv/integral/blob/master/privacy/PRIVACY-POLICY.md"
        const val TERMS_DEFAULT = "https://github.com/armancodv/integral/blob/master/privacy/TERMS-AND-CONDITIONS.md"
    }
}