package com.armanco.integral.utils.extensions

import com.armanco.integral.models.ConfigAds
import com.armanco.integral.models.ConfigLinks
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson

val FirebaseRemoteConfig.configLinks: ConfigLinks?
    get() {
        return Gson().fromJson(getString(ConfigLinks.KEY), ConfigLinks::class.java)
    }

val FirebaseRemoteConfig.configAds: ConfigAds?
    get() {
        return Gson().fromJson(getString(ConfigAds.KEY), ConfigAds::class.java)
    }