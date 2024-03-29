package com.stac.domain.usecases.search

import com.stac.domain.base.UseCase
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.domain.model.search.CategoryType
import com.stac.domain.repository.SearchRepository
import com.stac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetResultByCategory @Inject constructor(
    private val searchRepository: SearchRepository
) : UseCase<GetResultByCategory.Params, List<SearchResultByCategory>>() {


    override operator fun invoke(params: Params): Flow<Resource<List<SearchResultByCategory>>> = execute {
        searchRepository.getResultByCategory(params.page, params.type)
    }

    data class Params(
        val page: Int,
        val type: CategoryType

    )


}