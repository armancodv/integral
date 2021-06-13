package com.armanco.integral.service

import com.google.firebase.messaging.FirebaseMessagingService

class IntegralMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}