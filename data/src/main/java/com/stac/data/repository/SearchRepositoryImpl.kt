package com.stac.data.repository

import com.stac.data.datasource.SearchDataSource
import com.stac.data.mapper.toModel
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult
import com.stac.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
) : SearchRepository {
    override suspend fun getResultByName(name: String): SearchResult {

        return searchDataSource.getResultOfName(name)
    }

    override suspend fun getResultAll(page: Int, size: Int): List<SearchResultByCategory> {
        val list = mutableListOf<SearchResultByCategory>()
        searchDataSource.getResultAll(page, size).forEach {
            list.add(it.toModel())
        }
        return list
    }


    override suspend fun getResultByCategory(page: Int, type: CategoryType): List<SearchResultByCategory> {
        val list = mutableListOf<SearchResultByCategory>()
        searchDataSource.getResultOfCategory(page, type).forEach {
            list.add(it.toModel())
        }
        return list
    }

    override suspend fun getResult(keyword: String?, page: Int, type: CategoryType?): List<SearchResultByCategory> {
        val list = mutableListOf<SearchResultByCategory>()
        searchDataSource.getResult(keyword, page, type).forEach {
            list.add(it.toModel())
        }
        return list
    }
}