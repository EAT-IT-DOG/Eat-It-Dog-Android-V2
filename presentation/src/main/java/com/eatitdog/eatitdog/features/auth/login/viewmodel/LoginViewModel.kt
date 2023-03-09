package com.eatitdog.eatitdog.features.auth.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.eatitdog.eatitdog.base.BaseViewModel
import com.eatitdog.eatitdog.utils.MutableEventFlow
import com.eatitdog.eatitdog.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    val pw = MutableLiveData<String>()

    private var checked = false

    fun onClickRegister() {
        event(Event.OnClickRegister)
    }

    fun onClickPwToggle() {
        checked = !checked
        event(Event.PasswordToggle(checked))
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object OnClickRegister: Event()
        data class PasswordToggle(var checked: Boolean) : Event()
    }
}