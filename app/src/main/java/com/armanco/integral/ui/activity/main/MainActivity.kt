package com.armanco.integral.ui.activity.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.armanco.integral.AppConstants
import com.armanco.integral.BuildConfig
import com.armanco.integral.R
import com.armanco.integral.utils.extensions.configAds
import com.armanco.integral.utils.extensions.isIntegralPersianOrTrigonometryPersian
import com.armanco.integral.utils.extensions.isPro
import com.armanco.integral.utils.extensions.setLocale
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model: MainViewModel by viewModels()
        model.initEvents(this)
        model.configAds.observe(this) {
            initAdMob(when (BuildConfig.FLAVOR) {
                AppConstants.FLAVOR_PERSIAN -> {
                    it?.integralPersian?.bannerId
                }
                AppConstants.FLAVOR_FREE -> {
                    it?.integral?.bannerId
                }
                AppConstants.FLAVOR_TRIGONOMETRY -> {
                    it?.trigonometry?.bannerId
                }
                AppConstants.FLAVOR_TRIGONOMETRY_PERSIAN -> {
                    it?.trigonometryPersian?.bannerId
                }
                else -> null
            })
        }
        model.remoteConfig.fetchAndActivate().addOnCompleteListener(this) {
            model.configAds.postValue(model.remoteConfig.configAds)
        }
        initBottomNavigation()
    }

    override fun onResume() {
        super.onResume()
        if(isIntegralPersianOrTrigonometryPersian) {
            setLocale(Locale("fa"))
        }
    }

    private fun initAdMob(bannerId: String?) {
        if(bannerId.isNullOrBlank()) {
            removeAdMob()
            return
        }
        MobileAds.initialize(this)
        removeAdMob()
        val adView = AdView(this)
        adView.adSize = AdSize.BANNER
        adView.adUnitId = bannerId
        if (adView.adSize != null && adView.adUnitId != null) {
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
            adContainer?.addView(adView)
        }
    }

    private fun removeAdMob() {
        adContainer?.removeAllViews()
    }

    private fun initBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navHostFragment?.navController?.let { navController ->
            bottomNavigationView?.setupWithNavController(navController)
        }
    }
}