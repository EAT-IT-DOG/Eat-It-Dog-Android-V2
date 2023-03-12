package com.eatitdog.eatitdog.features.search.state

import com.eatitdog.domain.model.search.SearchResult

data class GetResultState(
    val isUpdate: Boolean = false,
    val result: List<SearchResult>? = null,
    val error: String = ""
)