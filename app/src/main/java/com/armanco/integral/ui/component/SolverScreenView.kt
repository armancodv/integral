package com.armanco.integral.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.armanco.integral.R
import kotlinx.android.synthetic.main.view_solver_screen.view.*

class SolverScreenView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var result: String?
        get() {
            return screenResult?.text?.toString()
        }
        set(value) {
            screenResult?.text = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_solver_screen, this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.SolverScreenView, 0, 0)
            .apply {
                try {
                    screenTitle?.text = getString(R.styleable.SolverScreenView_screenTitle)
                    screenSubtitle?.text = getString(R.styleable.SolverScreenView_screenSubtitle)
                    screenResult?.text = getString(R.styleable.SolverScreenView_screenResult)
                } finally {
                    recycle()
                }
            }
    }
}