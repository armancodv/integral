package com.armanco.integral.utils.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.armanco.integral.AppConstants
import com.armanco.integral.BuildConfig
import com.armanco.integral.data.models.ConfigAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import kotlin.random.Random


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


fun Activity.showInterstitialAd(interstitialId: String?) {
    if(interstitialId.isNullOrBlank()) return
    val adRequest = AdRequest.Builder().build()
    InterstitialAd.load(
        this,
        interstitialId,
        adRequest,
        object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                interstitialAd.show(this@showInterstitialAd)
            }
        })
}


fun Activity.showRewardedInterstitial(rewardedInterstitialId: String?, onUserEarnedRewardListener: OnUserEarnedRewardListener) {
    if(rewardedInterstitialId.isNullOrBlank()) return
    RewardedInterstitialAd.load(this, rewardedInterstitialId,
        AdRequest.Builder().build(), object : RewardedInterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: RewardedInterstitialAd) {
                ad.show(this@showRewardedInterstitial, onUserEarnedRewardListener)
            }
        })
}


fun Activity.showRewarded(rewardedId: String?, onUserEarnedRewardListener: OnUserEarnedRewardListener) {
    if(rewardedId.isNullOrBlank()) return
    RewardedAd.load(this, rewardedId,
        AdRequest.Builder().build(), object : RewardedAdLoadCallback() {
            override fun onAdLoaded(ad: RewardedAd) {
                ad.show(this@showRewarded, onUserEarnedRewardListener)
            }
        })
}

fun Activity.showRewardedRandom(configAds: ConfigAds?, adShown: ()->Unit): Boolean {
    val show = Random.nextInt(0, configAds?.randomSize ?: 2) == 0
    if(show) {
        showRewarded(when (BuildConfig.FLAVOR) {
            AppConstants.FLAVOR_PERSIAN -> {
                configAds?.integralPersian?.rewardedId
            }
            AppConstants.FLAVOR_FREE -> {
                configAds?.integral?.rewardedId
            }
            AppConstants.FLAVOR_TRIGONOMETRY -> {
                configAds?.trigonometry?.rewardedId
            }
            AppConstants.FLAVOR_TRIGONOMETRY_PERSIAN -> {
                configAds?.trigonometryPersian?.rewardedId
            }
            else -> null
        }) {
            adShown.invoke()
        }
    }
    return show
}