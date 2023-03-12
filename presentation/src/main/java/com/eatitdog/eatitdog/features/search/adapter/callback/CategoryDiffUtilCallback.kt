package com.eatitdog.eatitdog.features.search.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.eatitdog.domain.model.search.Category

object CategoryDiffUtilCallback : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
    }
}
