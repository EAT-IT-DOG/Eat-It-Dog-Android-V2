package com.eatitdog.eatitdog.features.search.state

import com.eatitdog.domain.model.search.SearchResultByCategory

data class GetResultByCategoryState(
    val isUpdate: Boolean = false,
    val result: List<SearchResultByCategory>? = null,
    val error: String = ""
)