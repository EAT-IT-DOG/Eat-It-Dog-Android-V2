package com.stac.domain.usecases.search

import com.stac.domain.base.UseCase
import com.stac.domain.model.search.SearchResult
import com.stac.domain.repository.SearchRepository
import com.stac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetResultByName @Inject constructor(
    private val searchRepository: SearchRepository
) : UseCase<String, SearchResult>() {


    override operator fun invoke(params: String): Flow<Resource<SearchResult>> = execute {
        searchRepository.getResultByName(params)
    }


}