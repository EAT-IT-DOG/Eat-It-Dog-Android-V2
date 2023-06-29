package com.stac.data.mapper

import com.stac.data.network.response.data.SearchOfCategoryResponse
import com.stac.data.network.response.data.SearchResponse
import com.stac.domain.model.search.SearchResult
import com.stac.domain.model.search.SearchResultByCategory

fun SearchOfCategoryResponse.toModel(category: String): SearchResultByCategory = SearchResultByCategory(
    name = this.name,
    category = category,
    grade = this.safeness
)

fun SearchResponse.toModel(): SearchResultByCategory = SearchResultByCategory(
    name = this.name,
    category = this.type,
    grade = this.safeness
)
fun SearchResult.toModel(): SearchResultByCategory = SearchResultByCategory(
    name = this.name,
    category = this.type,
    grade = this.safeness
)