package com.armanco.integral

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.armanco.integral.extensions.isPersian
import com.armanco.integral.extensions.isPro
import com.armanco.integral.extensions.setLocale
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!isPro) initAdMob()
        else removeAdMob()
        initToolbar()
    }

    override fun onResume() {
        super.onResume()
        if(isPersian) {
            setLocale(Locale("fa"))
        }
    }

    private fun initAdMob() {
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
    }

    private fun removeAdMob() {
        adView?.visibility = View.GONE
    }

    private fun initToolbar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navHostFragment?.navController?.let { navController ->
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            toolbar?.setupWithNavController(navController, appBarConfiguration)
        }
    }
}