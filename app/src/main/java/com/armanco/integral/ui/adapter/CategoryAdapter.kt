package com.armanco.integral.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.armanco.integral.R
import com.armanco.integral.data.models.Category
import com.armanco.integral.ui.component.CategoryView

class CategoryAdapter(var onCardClick: ((Category)->Unit)? = null): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var data: List<Category>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.get(position)?.let { category ->
            (holder.itemView as? CategoryView)?.apply {
                with(category)
                this.onCardClick = this@CategoryAdapter.onCardClick
            }
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}