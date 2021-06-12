package com.armanco.integral.ui.navigation.image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.utils.extensions.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image.*

@AndroidEntryPoint
class ImageFragment: Fragment(R.layout.fragment_image) {
    private val model: ImageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        model.configAds.observe(viewLifecycleOwner) {
            activity?.showRewardedRandom(it) {
                model.adShown()
            }
        }
        arguments?.getInt(IMAGE_KEY)?.let { photoView?.setImageResource(it) }
    }

    companion object {
        const val IMAGE_KEY = "image"
        const val TITLE_KEY = "title"
    }
}