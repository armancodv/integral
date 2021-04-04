package com.armanco.integral.navigation.image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.armanco.integral.R
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment: Fragment(R.layout.fragment_image) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(IMAGE_KEY)?.let { photoView?.setImageResource(it) }
    }
    companion object {
        const val IMAGE_KEY = "image"
    }
}