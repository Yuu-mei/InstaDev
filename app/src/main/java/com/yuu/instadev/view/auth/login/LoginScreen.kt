package com.yuu.instadev.view.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuu.instadev.R
import com.yuu.instadev.ui.theme.SuccessGreen
import com.yuu.instadev.view.core.components.InstaButton
import com.yuu.instadev.view.core.components.InstaText

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel(), navigateToSignUp: () -> Unit, navigateToTimeline: () -> Unit) {
    val uiState: LoginUIState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.success) {
        if(!uiState.isLoading && uiState.success){
            snackbarHostState.showSnackbar(
                message = "Loggin in...",
                duration = SnackbarDuration.Short
            )
            navigateToTimeline()
        }
    }

    LaunchedEffect(uiState.error) {
        if(!uiState.isLoading && uiState.error != null){
            snackbarHostState.showSnackbar(
                message = uiState.error!!,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        containerColor = if(uiState.success) SuccessGreen else MaterialTheme.colorScheme.error,
                        contentColor = if(uiState.success) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onError
                    )
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InstaText(
                text = stringResource(R.string.login_screen_language_selector_spain),
                modifier = Modifier
                    .padding(top = 22.dp)
                    .clickable(onClick = {}),
            )
            Spacer(Modifier.weight(1f))
            Image(
                modifier = Modifier.size(125.dp),
                painter = painterResource(R.drawable.instadev_logo),
                contentDescription = "Instadev Logo"
            )
            Spacer(Modifier.weight(1.3f))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = {
                    InstaText(text = stringResource(R.string.login_screen_textfield_email))
                },
                value = uiState.email,
                onValueChange = { loginViewModel.onEmailChanged(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(30)
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { InstaText(text = stringResource(R.string.login_screen_textfield_password)) },
                value = uiState.password,
                onValueChange = { loginViewModel.onPasswordChanged(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if(!uiState.showPassword) PasswordVisualTransformation() else VisualTransformation.None,
                shape = RoundedCornerShape(30),
                trailingIcon = {
                    IconButton(onClick = { loginViewModel.onPasswordVisibilityToggled() }) {
                        Icon(
                            painter = painterResource(if(!uiState.showPassword) R.drawable.ic_invisible else R.drawable.ic_visible),
                            contentDescription = if(!uiState.showPassword) "Hide Password" else "Show Password"
                        )
                    }
                }
            )
            Spacer(Modifier.height(10.dp))
            InstaButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val res = loginViewModel.onLoginClickedFirebase()
                    if(res) navigateToTimeline()
                },
                enabled = uiState.isLoggingEnabled,
                shape = MaterialTheme.shapes.extraLarge,
                text = stringResource(R.string.login_screen_button_login)
            )
            TextButton(onClick = {}) {
                InstaText(
                    text = stringResource(R.string.login_screen_text_button_forgot_password),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.weight(1f))
            InstaButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navigateToSignUp() },
                text = stringResource(R.string.login_screen_outlined_button_signup)
            )
            Image(
                painter = painterResource(R.drawable.ic_instadev_company),
                contentDescription = "Instadev Company Logo"
            )
        }
    }
}