package com.eatitdog.eatitdog.features.search.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import com.eatitdog.domain.model.search.SearchResult

object CategorySearchDiffUtilCallback : DiffUtil.ItemCallback<SearchResult>() {

    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem.name == newItem.name
    }
}
