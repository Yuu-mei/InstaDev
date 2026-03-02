package com.yuu.instadev.view.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuu.instadev.R
import com.yuu.instadev.view.core.components.InstaButton
import com.yuu.instadev.view.core.components.InstaText

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel(), navigateToSignUp: () -> Unit) {
    val uiState: LoginUIState by loginViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
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
                modifier = Modifier.padding(top = 22.dp),
            )
            Spacer(Modifier.weight(1f))
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(R.drawable.instadev_logo),
                contentDescription = "Instadev Logo"
            )
            Spacer(Modifier.weight(1.3f))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    InstaText(text = stringResource(R.string.login_screen_textfield_email))
                },
                value = uiState.email,
                onValueChange = { loginViewModel.onEmailChanged(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                shape = RoundedCornerShape(30)
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { InstaText(text = stringResource(R.string.login_screen_textfield_password)) },
                value = uiState.password,
                onValueChange = { loginViewModel.onPasswordChanged(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                shape = RoundedCornerShape(30),
            )
            Spacer(Modifier.height(10.dp))
            InstaButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { loginViewModel.onLoginClicked() },
                enabled = uiState.isLoadingEnabled,
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
            Icon(
                modifier = Modifier
                    .width(60.dp)
                    .padding(vertical = 22.dp),
                painter = painterResource(R.drawable.ic_meta),
                contentDescription = "Meta Logo",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}