package com.stac.eatitdog.features.result.state

import com.stac.domain.model.search.SearchResult

data class GetResultByNameState(
    val isUpdate: Boolean = false,
    val result: SearchResult? = null,
    val error: String = ""
)