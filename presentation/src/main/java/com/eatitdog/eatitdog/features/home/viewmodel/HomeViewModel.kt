package com.eatitdog.eatitdog.features.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.eatitdog.eatitdog.base.BaseViewModel
import com.eatitdog.eatitdog.features.result.viewmodel.ResultViewModel
import com.eatitdog.eatitdog.utils.MutableEventFlow
import com.eatitdog.eatitdog.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun onClickSearch() {
        event(Event.onClickSearch)
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object onClickSearch : Event()
    }
}