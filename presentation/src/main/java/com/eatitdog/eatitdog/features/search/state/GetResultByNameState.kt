package com.eatitdog.eatitdog.features.search.state

import com.eatitdog.domain.model.search.SearchResult
import com.eatitdog.domain.model.search.SearchResultByCategory

data class GetResultByNameState(
    val isUpdate: Boolean = false,
    val result: SearchResult? = null,
    val error: String = ""
)