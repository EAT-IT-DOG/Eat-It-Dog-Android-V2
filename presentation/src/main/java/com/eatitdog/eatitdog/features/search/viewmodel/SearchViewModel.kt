package com.eatitdog.eatitdog.features.search.viewmodel

import androidx.lifecycle.viewModelScope
import com.eatitdog.domain.model.search.CategoryType
import com.eatitdog.domain.usecases.search.GetResult
import com.eatitdog.domain.usecases.search.SearchUseCases
import com.eatitdog.eatitdog.base.BaseViewModel
import com.eatitdog.eatitdog.features.home.viewmodel.HomeViewModel
import com.eatitdog.eatitdog.features.search.state.GetResultByCategoryState
import com.eatitdog.eatitdog.utils.MutableEventFlow
import com.eatitdog.eatitdog.utils.asEventFlow
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
            GetResult.Params(0, 50)
        ).divideResult(
            isLoading,
            { _getResultByCategoryState.value = GetResultByCategoryState(result = it, isUpdate = true) },
            { _getResultByCategoryState.value = GetResultByCategoryState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    init {
        getResult()
    }

    sealed class Event {
        object onClickSearch : Event()
    }

}