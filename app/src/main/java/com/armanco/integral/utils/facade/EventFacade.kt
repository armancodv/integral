package com.armanco.integral.utils.facade

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.armanco.integral.utils.extensions.currentCountry
import com.armanco.integral.utils.extensions.currentLanguage
import com.google.firebase.analytics.FirebaseAnalytics

class EventFacade {
    private var firebaseAnalytics: FirebaseAnalytics? = null
    private var context: Context? = null
    fun init(context: Context?) {
        this.context = context
        firebaseAnalytics = context?.let { FirebaseAnalytics.getInstance(context) }
    }

    fun screenView(screen: String) {
        firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundleOf(
            FirebaseAnalytics.Param.SCREEN_NAME to screen,
            FirebaseAnalytics.Param.SCREEN_CLASS to screen,
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun selectCategory(id: Int, title: String?) {
        firebaseAnalytics?.logEvent(Event.SELECT_CATEGORY, bundleOf(
            Param.ID to id.toString(),
            Param.TITLE to title,
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun selectImage(id: Int, categoryId: Int, title: String?) {
        firebaseAnalytics?.logEvent(Event.SELECT_IMAGE, bundleOf(
            Param.ID to id.toString(),
            Param.CATEGORY to categoryId.toString(),
            Param.TITLE to title,
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun calculate(method: String?, function: String?, lowerLimit: Double?, upperLimit: Double?, steps: Int?, result: Double?) {
        firebaseAnalytics?.logEvent(Event.CALCULATE, bundleOf(
            Param.METHOD to method,
            Param.FUNCTION to function,
            Param.LOWER_LIMIT to lowerLimit.toString(),
            Param.UPPER_LIMIT to upperLimit.toString(),
            Param.STEPS to steps.toString(),
            Param.RESULT to result.toString(),
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun selectProVersion() {
        firebaseAnalytics?.logEvent(Event.SELECT_PRO_VERSION, bundleOf(
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun selectReportBug() {
        firebaseAnalytics?.logEvent(Event.SELECT_REPORT_BUG, bundleOf(
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun selectContribute() {
        firebaseAnalytics?.logEvent(Event.SELECT_CONTRIBUTE, bundleOf(
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun selectPrivacy() {
        firebaseAnalytics?.logEvent(Event.SELECT_PRIVACY, bundleOf(
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun selectTerms() {
        firebaseAnalytics?.logEvent(Event.SELECT_TERMS, bundleOf(
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun selectRate() {
        firebaseAnalytics?.logEvent(Event.SELECT_RATE, bundleOf(
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }

    fun submitRate() {
        firebaseAnalytics?.logEvent(Event.SUBMIT_RATE, bundleOf(
            Param.LANGUAGE to context?.currentLanguage,
            Param.COUNTRY to context?.currentCountry,
        ))
    }


    companion object {
        object Event {
            const val SELECT_CATEGORY = "select_category"
            const val SELECT_IMAGE = "select_image"
            const val CALCULATE = "calculate"
            const val SELECT_PRO_VERSION = "select_pro_version"
            const val SELECT_REPORT_BUG = "select_report_bug"
            const val SELECT_CONTRIBUTE = "select_contribute"
            const val SELECT_PRIVACY = "select_privacy"
            const val SELECT_TERMS = "select_terms"
            const val SELECT_RATE = "select_rate"
            const val SUBMIT_RATE = "submit_rate"
        }
        object Param {
            const val LANGUAGE = "language"
            const val COUNTRY = "country"
            const val ID = "id"
            const val TITLE = "title"
            const val CATEGORY = "category"
            const val METHOD = "method"
            const val FUNCTION = "function"
            const val LOWER_LIMIT = "lower_limit"
            const val UPPER_LIMIT = "upper_limit"
            const val RESULT = "result"
            const val STEPS = "steps"
        }
    }
}