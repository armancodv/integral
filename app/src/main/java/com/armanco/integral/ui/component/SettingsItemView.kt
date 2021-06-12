package com.armanco.integral.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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
                    val isRed = getBoolean(R.styleable.SettingsItemView_settingsIsRed, false)
                    if(isRed) {
                        ivTitle?.setColorFilter(ContextCompat.getColor(context, R.color.redDark))
                        setBackgroundColor(ContextCompat.getColor(context, R.color.redBackground))
                    }
                } finally {
                    recycle()
                }
            }
        clickableArea?.setOnClickListener {
            onClick?.invoke()
        }
    }
}