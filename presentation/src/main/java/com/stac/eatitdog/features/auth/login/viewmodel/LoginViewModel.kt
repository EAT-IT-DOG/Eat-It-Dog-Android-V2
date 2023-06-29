package com.stac.eatitdog.features.auth.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stac.domain.request.auth.LoginRequest
import com.stac.domain.usecases.auth.AuthUseCases
import com.stac.eatitdog.base.BaseViewModel
import com.stac.eatitdog.features.auth.login.state.LoginState
import com.stac.eatitdog.utils.MutableEventFlow
import com.stac.eatitdog.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : BaseViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    val pw = MutableLiveData<String>()

    private var checked = false

    fun onClickJoin() {
        event(Event.OnClickJoin)
    }

    fun onClickLogin() {
        event(Event.OnClickLogin)
    }

    fun onClickPwToggle() {
        checked = !checked
        event(Event.PasswordToggle(checked))
    }

    fun login(id: String, pw: String) {

        authUseCases.login(LoginRequest(id, pw)).divideResult(
            isLoading,
            { _loginState.value = LoginState(result = it, isUpdate = true) },
            { _loginState.value = LoginState(error = it ?: "로그인에 실패하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object OnClickJoin: Event()
        object OnClickLogin: Event()
        data class PasswordToggle(var checked: Boolean) : Event()
    }
}