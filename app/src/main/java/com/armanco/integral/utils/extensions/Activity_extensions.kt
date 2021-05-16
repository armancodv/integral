package com.armanco.integral.utils.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri


fun Activity.goToGooglePlay(appPackageName: String) {
    val intent = try {
        Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName"))
    } catch (activityNotFoundException: ActivityNotFoundException) {
        Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName"))
    }
    startActivity(intent)
}

fun Activity.goToGooglePlay() {
    goToGooglePlay(packageName)
}

fun Activity.goToUrl(url: String?) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}
