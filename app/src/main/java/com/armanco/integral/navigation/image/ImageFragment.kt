package com.armanco.integral.navigation.image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.utils.extensions.isPro
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
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
        model.configAds.observe(viewLifecycleOwner) {
            if(!isPro) showInterstitialAd(model.configAds.value?.interstitialId)
        }
        arguments?.getInt(IMAGE_KEY)?.let { photoView?.setImageResource(it) }
    }

    private fun showInterstitialAd(interstitialId: String?) {
        if (activity != null && interstitialId != null) {
            val adRequest = AdRequest.Builder().build()
            InterstitialAd.load(
                requireContext(),
                interstitialId,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        mInterstitialAd = interstitialAd
                        mInterstitialAd?.show(activity!!)
                    }
                })
        }
    }

    companion object {
        const val IMAGE_KEY = "image"
        const val TITLE_KEY = "title"
    }
}