package com.stac.eatitdog.features.search.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.stac.domain.model.search.CategoryType
import com.stac.domain.usecases.search.GetResult
import com.stac.domain.usecases.search.GetResultAll
import com.stac.domain.usecases.search.GetResultByCategory
import com.stac.domain.usecases.search.SearchUseCases
import com.stac.eatitdog.base.BaseViewModel
import com.stac.eatitdog.features.search.state.GetResultState
import com.stac.eatitdog.utils.MutableEventFlow
import com.stac.eatitdog.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : BaseViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _getResultState = MutableStateFlow(GetResultState())
    val getResultState: StateFlow<GetResultState> = _getResultState

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    fun onClickSearch() {
        event(Event.onClickSearch)
    }

    fun getResult(keyword: String?, page: Int, type: CategoryType?) {
        searchUseCases.getResult(GetResult.Params(keyword, page, type)).divideResult(
            isLoading,
            { _getResultState.value = GetResultState(result = it, isUpdate = true, paging = true, judgment = "search", page = page) },
            { _getResultState.value = GetResultState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getResultByCategory(page: Int, type: CategoryType) {
        searchUseCases.getResultByCategory(GetResultByCategory.Params(page, type)).divideResult(
            isLoading,
            { _getResultState.value = GetResultState(result = it, isUpdate = true, paging = true, judgment = type.toString(), page = page) },
            { _getResultState.value = GetResultState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getResultAll(page: Int) {
        searchUseCases.getResultAll(
            GetResultAll.Params(page, 10)
        ).divideResult(
            isLoading,
            { _getResultState.value = GetResultState(result = it, isUpdate = true, paging = true, judgment = "all", page = page) },
            { _getResultState.value = GetResultState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    sealed class Event {
        object onClickSearch : Event()
    }

}