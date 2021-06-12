package com.armanco.integral.utils.facade

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.armanco.integral.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

object AuthFacade {
    fun goToAuth(activity: Activity, privacyUrl: String?, termsUrl: String?) {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        activity.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.mipmap.ic_launcher)
                .setTheme(R.style.Theme_Integral)
                .apply {
                    if(!termsUrl.isNullOrBlank() && !privacyUrl.isNullOrBlank()) {
                        this.setTosAndPrivacyPolicyUrls(termsUrl, privacyUrl)
                    }
                }
                .build(),
            AUTH_UI_CODE)
    }

    fun goToAuth(fragment: Fragment, privacyUrl: String?, termsUrl: String?) {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        fragment.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.mipmap.ic_launcher)
                .setTheme(R.style.Theme_Integral)
                .apply {
                    if(!termsUrl.isNullOrBlank() && !privacyUrl.isNullOrBlank()) {
                        this.setTosAndPrivacyPolicyUrls(termsUrl, privacyUrl)
                    }
                }
                .build(),
            AUTH_UI_CODE)
    }

    fun signOut(context: Context): Task<Void> {
        return AuthUI.getInstance()
            .signOut(context)
    }

    fun deleteAccount(context: Context): Task<Void> {
        return AuthUI.getInstance()
            .delete(context)
    }

    fun onAuthResult(requestCode: Int, resultCode: Int, data: Intent?): Task<FirebaseUser> {
        if (requestCode == AUTH_UI_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                return Tasks.forResult(FirebaseAuth.getInstance().currentUser)
            }
        }
        val response = IdpResponse.fromResultIntent(data)
        return Tasks.forException(response?.error ?: Exception())
    }

    val isLoggedIn: Boolean
        get() {
            return !FirebaseAuth.getInstance().currentUser?.uid.isNullOrBlank()
        }

    val user: FirebaseUser?
        get() {
            return FirebaseAuth.getInstance().currentUser
        }

    private const val AUTH_UI_CODE = 2000
}