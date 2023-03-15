package com.stac.eatitdog.features.auth.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stac.eatitdog.base.BaseViewModel
import com.stac.eatitdog.utils.MutableEventFlow
import com.stac.eatitdog.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    val pw = MutableLiveData<String>()
    val pwCheck = MutableLiveData<String>()

    private var pwchecked = false
    private var pwcheckchecked = false

    fun onClickLogin() {
        event(Event.OnClickLogin)
    }

    fun onClickPwToggle() {
        pwchecked = !pwchecked
        event(Event.PasswordToggle(pwchecked))
    }

    fun onClickPwCheckToggle() {
        pwcheckchecked = !pwcheckchecked
        event(Event.PasswordCheckToggle(pwcheckchecked))
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object OnClickLogin: Event()
        data class PasswordToggle(var checked: Boolean) : Event()
        data class PasswordCheckToggle(var checked: Boolean) : Event()
    }
}