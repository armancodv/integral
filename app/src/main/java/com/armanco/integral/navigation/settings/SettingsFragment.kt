package com.armanco.integral.navigation.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.models.ConfigLinks
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
            activity?.goToGooglePlay(model.configLinks.value?.proPackageName ?: ConfigLinks.PRO_PACKAGE_NAME_DEFAULT)
        }
        reportBug?.onClick = {
            model.selectReportBug()
            activity?.goToUrl(model.configLinks.value?.reportBug ?: ConfigLinks.REPORT_BUG_DEFAULT)
        }
        contribute?.onClick = {
            model.selectContribute()
            activity?.goToUrl(model.configLinks.value?.contribute ?: ConfigLinks.CONTRIBUTE_DEFAULT)
        }
        privacy?.onClick = {
            model.selectPrivacy()
            activity?.goToUrl(model.configLinks.value?.privacy ?: ConfigLinks.PRIVACY_DEFAULT)
        }
        terms?.onClick = {
            model.selectTerms()
            activity?.goToUrl(model.configLinks.value?.terms ?: ConfigLinks.TERMS_DEFAULT)
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