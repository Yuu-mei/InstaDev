package com.yuu.instadev.view.auth.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState: StateFlow<SignUpUIState> = _uiState;

    fun onEmailChanged(email: String){
        _uiState.update { state ->
            state.copy(email = email)
        }
        if(isEmailValid(_uiState.value.email)) enableSignUpButton(true) else enableSignUpButton(false)
    }

    fun onPhoneChanged(phone: String){
        _uiState.update { state ->
            state.copy(phone = phone)
        }
        if(isPhoneValid(_uiState.value.phone)) enableSignUpButton(true) else enableSignUpButton(false)
    }

    fun invalidateSignUp(){
        _uiState.update { state ->
            state.copy(email = "", phone = "", enabledSignUp = false)
        }
    }
    private fun enableSignUpButton(enabled: Boolean){
        if(enabled) _uiState.update { state -> state.copy(enabledSignUp = true) } else _uiState.update { state -> state.copy(enabledSignUp = false) }
    }


    private fun isEmailValid(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isPhoneValid(phone: String): Boolean = phone.length == 9 && phone.toIntOrNull() != null
}

data class SignUpUIState(
    val email: String = "",
    val phone: String = "",
    val enabledSignUp: Boolean = false
)