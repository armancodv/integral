package com.armanco.integral.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.armanco.integral.R
import com.armanco.integral.models.Formula
import com.armanco.integral.view.component.FormulaView

class FormulaAdapter(var onCardClick: ((Formula)->Unit)? = null): RecyclerView.Adapter<FormulaAdapter.ViewHolder>() {

    var data: List<Formula>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_formula_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.get(position)?.let { formula ->
            (holder.itemView as? FormulaView)?.apply {
                with(formula)
                this.onCardClick = this@FormulaAdapter.onCardClick
            }
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}