package com.armanco.integral.utils.facade

import android.app.Activity
import android.content.Context
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task

class ReviewFacade {
    private var reviewInfo: ReviewInfo? = null
    private var manager: ReviewManager? = null
    private var requestFlow: Task<ReviewInfo>? = null

    var onFinished: (()->Unit)? = null

    fun init(context: Context?) {
        manager = context?.let { ReviewManagerFactory.create(it) }
        requestFlow = manager?.requestReviewFlow()
        requestFlow?.addOnCompleteListener { request ->
            reviewInfo = if (request.isSuccessful) {
                request.result
            } else {
                null
            }
        }
    }

    fun showReview(activity: Activity?) {
        reviewInfo?.let {
            val flow = activity?.let { act ->
                manager?.launchReviewFlow(act, it)
            }
            flow?.addOnCompleteListener {
                onFinished?.invoke()
            }
        }
    }
}