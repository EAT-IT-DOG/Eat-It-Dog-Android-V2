package com.eatitdog.domain.usecases.search

import com.eatitdog.domain.base.UseCase
import com.eatitdog.domain.model.search.SearchResultByCategory
import com.eatitdog.domain.model.search.CategoryType
import com.eatitdog.domain.repository.SearchRepository
import com.eatitdog.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetResultByCategory @Inject constructor(
    private val searchRepository: SearchRepository
) : UseCase<CategoryType, List<SearchResultByCategory>>() {


    override operator fun invoke(params: CategoryType): Flow<Resource<List<SearchResultByCategory>>> = execute {
        searchRepository.getResultByCategory(params)
    }


}