package com.armanco.integral.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.armanco.integral.R
import com.armanco.integral.models.Category
import kotlinx.android.synthetic.main.view_category.view.*

class CategoryView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    var onCardClick: ((Category)->Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_category, this, true)
    }

    fun with(category: Category) {
        tvTitle?.text = context.getString(category.stringRes)
        ivTitle?.setImageResource(category.drawableRes)
        setOnClickListener {
            onCardClick?.invoke(category)
        }
    }
}