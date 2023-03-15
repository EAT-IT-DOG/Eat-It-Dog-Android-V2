package com.stac.eatitdog.features.search.viewmodel

import androidx.lifecycle.viewModelScope
import com.stac.domain.model.search.CategoryType
import com.stac.domain.usecases.search.GetResult
import com.stac.domain.usecases.search.SearchUseCases
import com.stac.eatitdog.base.BaseViewModel
import com.stac.eatitdog.features.search.state.GetResultByCategoryState
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

    private val _getResultByCategoryState = MutableStateFlow(GetResultByCategoryState())
    val getResultByCategoryState: StateFlow<GetResultByCategoryState> = _getResultByCategoryState

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    fun onClickSearch() {
        event(Event.onClickSearch)
    }

    fun getResultByCategory(type: CategoryType) {
        searchUseCases.getResultByCategory(type).divideResult(
            isLoading,
            { _getResultByCategoryState.value = GetResultByCategoryState(result = it, isUpdate = true) },
            { _getResultByCategoryState.value = GetResultByCategoryState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getResult() {
        searchUseCases.getResult(
            GetResult.Params(0, 200)
        ).divideResult(
            isLoading,
            { _getResultByCategoryState.value = GetResultByCategoryState(result = it, isUpdate = true) },
            { _getResultByCategoryState.value = GetResultByCategoryState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    sealed class Event {
        object onClickSearch : Event()
    }

}