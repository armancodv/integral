package com.armanco.integral.ui.navigation.settings

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.data.models.ConfigLinks
import com.armanco.integral.utils.extensions.goToGooglePlay
import com.armanco.integral.utils.extensions.goToUrl
import com.armanco.integral.utils.extensions.isPro
import com.armanco.integral.utils.facade.AuthFacade
import com.armanco.integral.utils.facade.ReviewFacade
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val model: SettingsViewModel by viewModels()

    private val reviewFacade = ReviewFacade()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        reviewFacade.init(context)
        proVersion?.visibility = if (isPro) View.GONE else View.VISIBLE
        scrollView?.let {
            OverScrollDecoratorHelper.setUpOverScroll(it)
        }
        model.isLoggedIn.observe(viewLifecycleOwner) {
            delete?.visibility = if (it) View.VISIBLE else View.GONE
            logout?.visibility = if (it) View.VISIBLE else View.GONE
            login?.visibility = if (it) View.GONE else View.VISIBLE
        }
        proVersion?.onClick = {
            model.selectProVersion()
            activity?.goToGooglePlay(
                model.configLinks.value?.proPackageName ?: ConfigLinks.PRO_PACKAGE_NAME_DEFAULT
            )
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
        delete?.onClick = {
            showDeleteDialog()
        }
        logout?.onClick = {
            showLogoutDialog()
        }
        login?.onClick = {
            model.selectLogin()
            AuthFacade.goToAuth(
                this,
                model.configLinks.value?.privacy,
                model.configLinks.value?.terms
            )
        }
        reviewFacade.onFinished = {
            model.submitRate()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        AuthFacade.onAuthResult(requestCode, resultCode, data).addOnCompleteListener {
            if (it.result != null) model.submitLogin()
        }
    }

    private fun showDeleteDialog() {
        context?.let { ctx ->
            val dialogClickListener =
                DialogInterface.OnClickListener { _, type ->
                    if(type == DialogInterface.BUTTON_POSITIVE) {
                        model.selectDelete(ctx)
                    }
                }

            val builder: AlertDialog.Builder = AlertDialog.Builder(ctx)
            builder.setMessage(getString(R.string.dialog_delete_user_text))
                .setPositiveButton(getString(R.string.settings_delete), dialogClickListener)
                .setNegativeButton(getString(R.string.cancel), dialogClickListener).show()
        }
    }

    private fun showLogoutDialog() {
        context?.let { ctx ->
            val dialogClickListener =
                DialogInterface.OnClickListener { _, type ->
                    if(type == DialogInterface.BUTTON_POSITIVE) {
                        model.selectLogout(ctx)
                    }
                }

            val builder: AlertDialog.Builder = AlertDialog.Builder(ctx)
            builder.setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.settings_logout), dialogClickListener)
                .setNegativeButton(getString(R.string.cancel), dialogClickListener).show()
        }
    }
}