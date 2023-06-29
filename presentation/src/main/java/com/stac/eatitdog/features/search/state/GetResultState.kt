package com.stac.eatitdog.features.search.state

import com.stac.domain.model.search.SearchResultByCategory

data class GetResultState(
    val isUpdate: Boolean = false,
    val result: List<SearchResultByCategory>? = null,
    val error: String = "",
    val paging: Boolean = false,
    val judgment: String = "",
    val page: Int = 0
)