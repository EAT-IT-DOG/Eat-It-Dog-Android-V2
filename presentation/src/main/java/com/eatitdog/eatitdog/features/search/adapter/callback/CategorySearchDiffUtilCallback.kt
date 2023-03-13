package com.eatitdog.eatitdog.features.search.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.eatitdog.domain.model.search.SearchResultByCategory

object CategorySearchDiffUtilCallback : DiffUtil.ItemCallback<SearchResultByCategory>() {

    override fun areItemsTheSame(oldItem: SearchResultByCategory, newItem: SearchResultByCategory): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SearchResultByCategory, newItem: SearchResultByCategory): Boolean {
        return oldItem.name == newItem.name
    }
}
