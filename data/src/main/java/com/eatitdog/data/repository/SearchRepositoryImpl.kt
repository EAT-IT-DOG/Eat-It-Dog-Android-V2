package com.eatitdog.data.repository

import com.eatitdog.data.datasource.SearchDataSource
import com.eatitdog.data.mapper.toModel
import com.eatitdog.domain.model.search.SearchResultByCategory
import com.eatitdog.domain.model.search.CategoryType
import com.eatitdog.domain.model.search.SearchResult
import com.eatitdog.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getResultByName(name: String): SearchResult {

        return searchDataSource.getResultOfName(name)
    }

    override suspend fun getResult(page: Int, size: Int): List<SearchResultByCategory> {
        val list = mutableListOf<SearchResultByCategory>()
        searchDataSource.getResult(page, size).forEach {
            list.add(it.toModel())
        }
        return list
    }


    override suspend fun getResultByCategory(categoryType: CategoryType): List<SearchResultByCategory> {
        val list = mutableListOf<SearchResultByCategory>()
        searchDataSource.getResultOfCategory(categoryType).forEach {
            list.add(it.toModel(categoryType.toString()))
        }
        return list
    }
}