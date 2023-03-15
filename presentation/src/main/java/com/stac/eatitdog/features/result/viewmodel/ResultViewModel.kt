package com.stac.eatitdog.features.result.viewmodel

import androidx.lifecycle.viewModelScope
import com.stac.domain.usecases.search.SearchUseCases
import com.stac.eatitdog.base.BaseViewModel
import com.stac.eatitdog.features.search.state.GetResultByNameState
import com.stac.eatitdog.utils.MutableEventFlow
import com.stac.eatitdog.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : BaseViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _getResultByNameState = MutableStateFlow(GetResultByNameState())
    val getResultByNameState: StateFlow<GetResultByNameState> = _getResultByNameState

    fun getResultByName(name: String) {
        searchUseCases.getResultByName(name).divideResult(
            isLoading,
            { _getResultByNameState.value = GetResultByNameState(result = it, isUpdate = true) },
            { _getResultByNameState.value = GetResultByNameState(error = it ?: "음식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickConfirm() {
        event(Event.onClickConfirm)
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object onClickConfirm : Event()
    }
}