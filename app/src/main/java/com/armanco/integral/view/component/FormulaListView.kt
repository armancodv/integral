package com.armanco.integral.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.armanco.integral.R
import com.armanco.integral.models.Formula
import com.armanco.integral.view.adapter.FormulaAdapter
import kotlinx.android.synthetic.main.view_formula_list.view.*

class FormulaListView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {

    var onCardClick: ((Formula)->Unit)? = null
    private var adapter = FormulaAdapter()
    private var layoutManager =  LinearLayoutManager(context)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_formula_list, this, true)
        rvCategory?.adapter = adapter
        rvCategory?.layoutManager = layoutManager
    }

    fun with(categories: List<Formula>) {
        adapter.onCardClick = onCardClick
        adapter.data = categories
    }
}