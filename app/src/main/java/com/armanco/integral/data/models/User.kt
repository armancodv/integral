package com.armanco.integral.data.models

import com.google.firebase.auth.FirebaseUser

data class User(
    val userId: String? = null,
    var name: String? = null,
    var email: String? = null,
    var photoUrl: String? = null,
    val emailVerified: Boolean? = null,
    var phoneNumber: String? = null,
    var xp: Int? = null,
    var counterPlot: Int? = null,
    var counterCalculate: Int? = null,
    var counterFormula: Int? = null,
    var counterAd: Int? = null,
) {
    fun toMapRemoveNull(): Map<String, Any> {
        val hashMap = hashMapOf<String, Any>()
        userId?.let { hashMap[USER_ID] = it }
        name?.let { hashMap[NAME] = it }
        email?.let { hashMap[EMAIL] = it }
        photoUrl?.let { hashMap[PHOTO_URL] = it }
        emailVerified?.let { hashMap[EMAIL_VERIFIED] = it }
        phoneNumber?.let { hashMap[PHONE_NUMBER] = it }
        xp?.let { if(it>0) hashMap[XP] = it }
        counterPlot?.let { if(it>0) hashMap[COUNTER_PLOT] = it }
        counterCalculate?.let { if(it>0) hashMap[COUNTER_CALCULATE] = it }
        counterFormula?.let { if(it>0) hashMap[COUNTER_FORMULA] = it }
        counterAd?.let { if(it>0) hashMap[COUNTER_AD] = it }
        return hashMap
    }

    companion object {
        const val USER_ID = "userId"
        const val NAME = "name"
        const val EMAIL = "email"
        const val PHOTO_URL = "photoUrl"
        const val EMAIL_VERIFIED = "emailVerified"
        const val PHONE_NUMBER = "phoneNumber"
        const val XP = "xp"
        const val COUNTER_PLOT = "counterPlot"
        const val COUNTER_CALCULATE = "counterCalculate"
        const val COUNTER_FORMULA = "counterFormula"
        const val COUNTER_AD = "counterAd"

        fun fromFirebaseUser(firebaseUser: FirebaseUser): User {
            return User(
                userId = firebaseUser.uid,
                name = firebaseUser.displayName,
                email = firebaseUser.email,
                photoUrl = firebaseUser.photoUrl?.toString(),
                emailVerified = firebaseUser.isEmailVerified,
                phoneNumber = firebaseUser.phoneNumber
            )
        }
    }
}