package com.eatitdog.domain.usecases.search

import com.eatitdog.domain.base.UseCase
import com.eatitdog.domain.model.search.SearchResult
import com.eatitdog.domain.model.search.SearchResultByCategory
import com.eatitdog.domain.repository.SearchRepository
import com.eatitdog.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetResultByName @Inject constructor(
    private val searchRepository: SearchRepository
) : UseCase<String, SearchResult>() {


    override operator fun invoke(params: String): Flow<Resource<SearchResult>> = execute {
        searchRepository.getResultByName(params)
    }


}