package com.armanco.integral.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.armanco.integral.R
import com.armanco.integral.data.models.Formula
import com.armanco.integral.ui.adapter.FormulaAdapter
import kotlinx.android.synthetic.main.view_formula_list.view.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class FormulaListView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    var onCardClick: ((Formula)->Unit)? = null
    private var adapter = FormulaAdapter()
    private var layoutManager =  LinearLayoutManager(context)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_formula_list, this, true)
        rvCategory?.let {
            it.adapter = adapter
            it.layoutManager = layoutManager
            OverScrollDecoratorHelper.setUpOverScroll(it, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
        }
    }

    fun with(categories: List<Formula>) {
        adapter.onCardClick = onCardClick
        adapter.data = categories
    }
}