package com.armanco.integral.ui.navigation.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.utils.facade.AuthFacade
import com.bumptech.glide.Glide
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val model: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.load()
        login?.setOnClickListener {
            model.selectLogin()
            AuthFacade.goToAuth(
                this,
                model.configLinks.value?.privacy,
                model.configLinks.value?.terms
            )
        }
        model.isLoggedIn.observe(viewLifecycleOwner) {
            login?.visibility = if (it) View.GONE else View.VISIBLE
            view.post {
                blurView?.visibility = View.GONE
                scrollView?.visibility = View.VISIBLE
                if (!it) {
                    if (scrollView != null && blurView != null) {
                        blurView?.visibility = View.VISIBLE
                        Blurry.with(context)
                            .radius(10)
                            .sampling(8)
                            .capture(scrollView)
                            .into(blurView)
                        scrollView?.visibility = View.GONE
                    }
                }
            }

        }
        model.firebaseUser.observe(viewLifecycleOwner) {
            name?.text = if (!it?.displayName.isNullOrBlank()) it?.displayName else it?.email
            Glide.with(this).load(it?.photoUrl).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(image)
        }
        model.xp.observe(viewLifecycleOwner) {
            xp?.text = context?.getString(R.string.xp, it.toString())
        }
        model.level.observe(viewLifecycleOwner) {
            level?.text = context?.getString(R.string.level, it.toString())
        }
        model.progress.observe(viewLifecycleOwner) {
            progress?.progress = it
        }
        model.counterPlot.observe(viewLifecycleOwner) {
            statPlot?.statValue = it.toString()
        }
        model.counterCalculate.observe(viewLifecycleOwner) {
            statCalculate?.statValue = it.toString()
        }
        model.counterFormula.observe(viewLifecycleOwner) {
            statFormula?.statValue = it.toString()
        }
        model.counterAd.observe(viewLifecycleOwner) {
            statAdView?.statValue = it.toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        AuthFacade.onAuthResult(requestCode, resultCode, data).addOnCompleteListener {
            if (it.result != null) model.submitLogin()
        }.addOnFailureListener {
            FirebaseCrashlytics.getInstance().recordException(it)
        }
    }
}