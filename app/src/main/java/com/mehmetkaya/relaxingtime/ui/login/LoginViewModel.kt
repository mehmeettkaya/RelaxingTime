package com.mehmetkaya.relaxingtime.ui.login

import androidx.lifecycle.viewModelScope
import com.mehmetkaya.core.Event
import com.mehmetkaya.core.StatefulViewModel
import com.mehmetkaya.core.UiState
import com.mehmetkaya.core.launch
import com.mehmetkaya.relaxingtime.data.local.LocalStorageModule.USER_NAME_PREF
import com.mehmetkaya.relaxingtime.ui.login.LoginViewModel.LoginEvent
import com.mehmetkaya.relaxingtime.ui.login.LoginViewModel.LoginEvent.NavigateToHome
import com.mehmetkaya.relaxingtime.ui.login.LoginViewModel.LoginState
import com.mehmetkaya.utils.exts.isValidPassword
import com.mehmetkaya.utils.exts.isValidUserName
import com.mehmetkaya.utils.preference.StringPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginViewModel @Inject constructor(
    @Named(USER_NAME_PREF) private val userNamePref: StringPreference
) : StatefulViewModel<LoginState, LoginEvent>(LoginState()) {

    fun continueButtonClicked(userName: String) {
        launch {
            userNamePref.set(userName)

            pushEvent(NavigateToHome)
        }
    }

    fun onFormChange(userNameChanges: Flow<String>, passwordChanges: Flow<String>) {
        combine(
            userNameChanges,
            passwordChanges,
            ::validateForm
        ).launchIn(viewModelScope)
    }

    private fun validateForm(userName: String, password: String) {
        val isValid = userName.isValidUserName() && password.isValidPassword()

        setState { copy(isValid = isValid) }
    }

    data class LoginState(
        val isValid: Boolean = false
    ) : UiState

    sealed class LoginEvent : Event {
        object NavigateToHome : LoginEvent()
    }
}
