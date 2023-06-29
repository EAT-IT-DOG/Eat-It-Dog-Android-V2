package com.stac.eatitdog.features.auth.join.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stac.domain.request.auth.JoinRequest
import com.stac.domain.usecases.auth.AuthUseCases
import com.stac.eatitdog.base.BaseViewModel
import com.stac.eatitdog.features.auth.join.state.JoinState
import com.stac.eatitdog.utils.MutableEventFlow
import com.stac.eatitdog.utils.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : BaseViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    val pw = MutableLiveData<String>()
    val pwCheck = MutableLiveData<String>()

    private val _joinState = MutableStateFlow(JoinState())
    val joinState: StateFlow<JoinState> = _joinState

    private var pwchecked = false
    private var pwcheckchecked = false

    fun onClickLogin() {
        event(Event.OnClickLogin)
    }

    fun onClickJoin() {
        event(Event.OnClickJoin)
    }

    fun onClickPwToggle() {
        pwchecked = !pwchecked
        event(Event.PasswordToggle(pwchecked))
    }

    fun onClickPwCheckToggle() {
        pwcheckchecked = !pwcheckchecked
        event(Event.PasswordCheckToggle(pwcheckchecked))
    }

    fun join(joinRequest: JoinRequest) {
        authUseCases.join(joinRequest).divideResult(
            isLoading,
            { _joinState.value = JoinState(result = it, isUpdate = true) },
            { _joinState.value = JoinState(error = it ?: "회원가입에 실패하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object OnClickLogin: Event()
        object OnClickJoin: Event()
        data class PasswordToggle(var checked: Boolean) : Event()
        data class PasswordCheckToggle(var checked: Boolean) : Event()
    }
}