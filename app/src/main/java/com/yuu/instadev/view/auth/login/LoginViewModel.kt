package com.yuu.instadev.view.auth.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState:StateFlow<LoginUIState> = _uiState

    fun onEmailChanged(email: String){
        _uiState.update { state ->
            state.copy(email = email)
        }
        verifyFields()
    }

    fun onPasswordChanged(password:String){
        _uiState.update { state ->
            state.copy(password = password)
        }
        verifyFields()
    }

    private fun verifyFields(){
        val enabledLogin = isEmailValid(_uiState.value.email) && isPasswordValid(_uiState.value.password)
        _uiState.update { state ->
            state.copy(isLoadingEnabled = enabledLogin)
        }
    }

    private fun isEmailValid(email:String):Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isPasswordValid(password:String):Boolean = password.length >= 6
}

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoadingEnabled: Boolean = false
)