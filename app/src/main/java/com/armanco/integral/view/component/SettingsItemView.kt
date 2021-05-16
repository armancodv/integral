package com.armanco.integral.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.armanco.integral.R
import kotlinx.android.synthetic.main.view_settings_item.view.*

class SettingsItemView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    var onClick: (()->Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_settings_item, this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.SettingsItemView, 0, 0)
            .apply {
                try {
                    tvTitle?.text = getString(R.styleable.SettingsItemView_settingsTitle)
                    ivTitle?.setImageDrawable(getDrawable(R.styleable.SettingsItemView_settingsImage))
                    ivTitle?.imageTintList = getColorStateList(R.styleable.SettingsItemView_settingsImageTint)
                } finally {
                    recycle()
                }
            }
        clickableArea?.setOnClickListener {
            onClick?.invoke()
        }
    }
}