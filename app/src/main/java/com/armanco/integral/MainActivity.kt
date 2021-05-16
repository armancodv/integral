package com.armanco.integral

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.armanco.integral.utils.extensions.isPersian
import com.armanco.integral.utils.extensions.isPro
import com.armanco.integral.utils.extensions.setLocale
import com.armanco.integral.utils.facade.EventFacade
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var eventFacade: EventFacade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!isPro) initAdMob()
        else removeAdMob()
        initToolbar()
        eventFacade.init(this)
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