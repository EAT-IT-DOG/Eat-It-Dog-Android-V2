package com.eatitdog.domain.usecases.search

import com.eatitdog.domain.base.UseCase
import com.eatitdog.domain.model.search.SearchResultByCategory
import com.eatitdog.domain.repository.SearchRepository
import com.eatitdog.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetResult @Inject constructor(
    private val searchRepository: SearchRepository
) : UseCase<GetResult.Params, List<SearchResultByCategory>>() {


    override operator fun invoke(params: Params): Flow<Resource<List<SearchResultByCategory>>> = execute {
        searchRepository.getResult(params.page, params.size)
    }

    data class Params(
        val page: Int,
        val size: Int
    )

}