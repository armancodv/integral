package com.armanco.integral.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.armanco.integral.R
import com.armanco.integral.models.Formula
import kotlinx.android.synthetic.main.view_formula.view.*

class FormulaView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    var onCardClick: ((Formula)->Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_formula, this, true)
    }

    fun with(formula: Formula) {
        tvTitle?.text = context.getString(formula.stringRes)
        ivFormula?.setImageResource(formula.drawableRes)
        setOnClickListener {
            onCardClick?.invoke(formula)
        }
    }
}