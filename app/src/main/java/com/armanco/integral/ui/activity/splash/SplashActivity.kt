package com.armanco.integral.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.armanco.integral.R
import com.armanco.integral.ui.activity.main.MainActivity
import com.armanco.integral.utils.extensions.configAds
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val model: SplashViewModel by viewModels()
        model.load()
        model.isReady.observe(this) {
            if(it) goToMain()
        }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

}