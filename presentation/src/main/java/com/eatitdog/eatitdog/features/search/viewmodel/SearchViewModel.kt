package com.eatitdog.eatitdog.features.search.viewmodel

import androidx.lifecycle.viewModelScope
import com.eatitdog.domain.model.search.CategoryType
import com.eatitdog.domain.usecases.search.GetResult
import com.eatitdog.domain.usecases.search.SearchUseCases
import com.eatitdog.eatitdog.base.BaseViewModel
import com.eatitdog.eatitdog.features.search.state.GetResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : BaseViewModel() {

    private val _getResultState = MutableStateFlow(GetResultState())
    val getResultState: StateFlow<GetResultState> = _getResultState

    fun getResultByCategory(type: CategoryType) {
        searchUseCases.getResultByCategory(type).divideResult(
            isLoading,
            { _getResultState.value = GetResultState(result = it, isUpdate = true) },
            { _getResultState.value = GetResultState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getResult() {
        searchUseCases.getResult(
            GetResult.Params(0, 50)
        ).divideResult(
            isLoading,
            { _getResultState.value = GetResultState(result = it, isUpdate = true) },
            { _getResultState.value = GetResultState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    init {
        getResult()
    }

}