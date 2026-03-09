package com.yuu.instadev.view.auth.signup

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuu.instadev.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val signUp: SignUpUseCase): ViewModel(){
    private val _uiState = MutableStateFlow(SignUpUIState())
    val uiState: StateFlow<SignUpUIState> = _uiState;

    fun onEmailChanged(email: String){
        _uiState.update { state ->
            state.copy(email = email)
        }
    }

    fun onPhoneChanged(phone: String){
        _uiState.update { state ->
            state.copy(phone = phone)
        }
    }

    fun onPasswordChanged(password: String){
        _uiState.update { state ->
            state.copy(password = password)
        }
        enableSignUpButton()
    }

    fun onPasswordVisibilityToggled(){
        _uiState.update { state ->
            state.copy(showPassword = !state.showPassword)
        }
    }

    fun invalidateSignUp(){
        _uiState.update { state ->
            state.copy(email = "", phone = "", enabledSignUp = false)
        }
    }

    fun signUpFirebase(){
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true , success = false, error = null)
            }

            try {
                val user = signUp.invoke(_uiState.value.email, _uiState.value.password)

                _uiState.update { state ->
                    state.copy(isLoading = false, success = true, error = null)
                }
                Log.i("SIGN UP", " SUCCESS")
            } catch (e: Exception){
                _uiState.update { state ->
                    state.copy(isLoading = false, success = false, error = e.message)
                }
                Log.e("SIGN UP ERROR", "${e.message}")
            }
        }
    }

    private fun enableSignUpButton(){
        if((isEmailValid(_uiState.value.email) || isPhoneValid(_uiState.value.email)) && isPasswordValid(_uiState.value.password)) {
            _uiState.update { state -> state.copy(enabledSignUp = true) }
        }else{
            _uiState.update { state -> state.copy(enabledSignUp = false) }
        }
    }


    private fun isEmailValid(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    private fun isPhoneValid(phone: String): Boolean = phone.length == 9 && phone.toIntOrNull() != null
    private fun isPasswordValid(password: String): Boolean {
        val regex = Regex(
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}\$"
        )
        return regex.matches(password)
    }
}

data class SignUpUIState(
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val enabledSignUp: Boolean = false,
    val isLoading: Boolean = false,
    val showPassword: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)