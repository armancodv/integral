package com.armanco.integral.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.armanco.integral.R
import kotlinx.android.synthetic.main.view_profile_stat.view.*

class ProfileStatView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var statValue: String?
        get() {
            return value?.text?.toString()
        }
    set(value) {
        this.value?.text = value
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_profile_stat, this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.ProfileStatView, 0, 0)
            .apply {
                try {
                    title?.text = getString(R.styleable.ProfileStatView_profileStatTitle)
                    image?.setImageDrawable(getDrawable(R.styleable.ProfileStatView_profileStatImage))
                    value?.text = getString(R.styleable.ProfileStatView_profileStatValue)
                } finally {
                    recycle()
                }
            }
    }
}