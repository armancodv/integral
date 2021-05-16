package com.armanco.integral.navigation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.utils.extensions.goToGooglePlay
import com.armanco.integral.utils.extensions.goToUrl
import com.armanco.integral.utils.extensions.isPro
import com.armanco.integral.utils.facade.ReviewFacade
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*

@AndroidEntryPoint
class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private val model: SettingsViewModel by viewModels()

    private val reviewFacade = ReviewFacade()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        reviewFacade.init(context)
        proVersion?.visibility = if(isPro) View.GONE else View.VISIBLE
        proVersion?.onClick = {
            model.selectProVersion()
            activity?.goToGooglePlay("com.armanco.integral_pro")
        }
        reportBug?.onClick = {
            model.selectReportBug()
            activity?.goToUrl("https://github.com/armancodv/integral/issues")
        }
        contribute?.onClick = {
            model.selectContribute()
            activity?.goToUrl("https://github.com/armancodv/integral")
        }
        rate?.onClick = {
            model.selectRate()
            reviewFacade.showReview(activity)
        }
        reviewFacade.onFinished = {
            model.submitRate()
        }
    }
}