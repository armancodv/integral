package com.armanco.integral.navigation.image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.utils.extensions.isPro
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.LoadAdError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image.*

@AndroidEntryPoint
class ImageFragment: Fragment(R.layout.fragment_image) {
    private val model: ImageViewModel by viewModels()

    private var mInterstitialAd: InterstitialAd? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        if(!isPro) showInterstitialAd()
        arguments?.getInt(IMAGE_KEY)?.let { photoView?.setImageResource(it) }
    }

    private fun showInterstitialAd() {
        mInterstitialAd = InterstitialAd(activity)
        mInterstitialAd?.adUnitId = "ca-app-pub-4301546764905932/6653178697"
        val adRequest = AdRequest.Builder().build()
        mInterstitialAd?.loadAd(adRequest)
        mInterstitialAd?.adListener = object : AdListener() {
            override fun onAdLoaded() {
                mInterstitialAd?.show()
            }
            override fun onAdFailedToLoad(adError: LoadAdError) {}
            override fun onAdOpened() {}
            override fun onAdClicked() {}
            override fun onAdLeftApplication() {}
            override fun onAdClosed() {}
        }
    }

    companion object {
        const val IMAGE_KEY = "image"
        const val TITLE_KEY = "title"
    }
}