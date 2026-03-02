@file:OptIn(ExperimentalMaterial3Api::class)

package com.yuu.instadev.view.auth.signup

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuu.instadev.R
import com.yuu.instadev.view.core.components.InstaButton
import com.yuu.instadev.view.core.components.InstaOutlinedButton
import com.yuu.instadev.view.core.components.InstaText
import com.yuu.instadev.view.core.components.InstaTextField

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val uiState: SignUpUIState by signUpViewModel.uiState.collectAsStateWithLifecycle()
    var isPhoneSignUp by remember { mutableStateOf(true) }

    //Setting up vars for the texts themselves
    var title: String
    var desc: String
    var label: String
    var warningText: String
    var outlinedButtonText: String

    if (isPhoneSignUp) {
        title = stringResource(R.string.signup_screen_whats_phone_number_text)
        desc = stringResource(R.string.signup_screen_phone_number_desc)
        label = stringResource(R.string.signup_screen_textfield_phone_number)
        warningText = stringResource(R.string.signup_screen_phone_warning_text)
        outlinedButtonText = stringResource(R.string.signup_screen_outlined_button_use_email)
    } else {
        title = stringResource(R.string.signup_screen_whats_email_text)
        desc = stringResource(R.string.signup_screen_email_desc)
        label = stringResource(R.string.signup_screen_textfield_email)
        warningText = stringResource(R.string.signup_screen_email_warning_text)
        outlinedButtonText = stringResource(R.string.signup_screen_outlined_button_use_phone)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { navigateBack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Go back",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AnimatedContent(title) { animatedTitle ->
                InstaText(
                    text = animatedTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            InstaText(
                text = desc,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            InstaTextField(
                modifier = Modifier.fillMaxWidth(),
                label = label,
                value = if(isPhoneSignUp) uiState.phone else uiState.email,
                onValueChanged = {
                    if(isPhoneSignUp) signUpViewModel.onPhoneChanged(it) else signUpViewModel.onEmailChanged(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = if(isPhoneSignUp) KeyboardType.Phone else KeyboardType.Email
                ),
            )
            InstaText(
                text = warningText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            InstaButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                enabled = uiState.enabledSignUp,
                text = stringResource(R.string.signup_screen_button_next)
            )
            InstaOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    isPhoneSignUp = !isPhoneSignUp
                    signUpViewModel.invalidateSignUp()
                },
                text = outlinedButtonText,
                titleColor = MaterialTheme.colorScheme.onPrimary,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
            )
            Spacer(Modifier.weight(1f))
            TextButton(
                onClick = {}
            ) {
                InstaText(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.signup_screen_text_button_find_account),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}