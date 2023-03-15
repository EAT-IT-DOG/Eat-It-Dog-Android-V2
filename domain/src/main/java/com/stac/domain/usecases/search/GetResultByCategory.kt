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
) : UseCase<CategoryType, List<SearchResultByCategory>>() {


    override operator fun invoke(params: CategoryType): Flow<Resource<List<SearchResultByCategory>>> = execute {
        searchRepository.getResultByCategory(params)
    }


}