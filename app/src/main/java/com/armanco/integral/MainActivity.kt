package com.armanco.integral

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdMob()
        initToolbar()
    }

    private fun initAdMob() {
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
    }

    private fun initToolbar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navHostFragment?.navController?.let { navController ->
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            toolbar?.setupWithNavController(navController, appBarConfiguration)
        }
    }
}