package com.yuu.instadev.view.core.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yuu.instadev.R
import com.yuu.instadev.view.core.components.InstaButton
import com.yuu.instadev.view.core.components.InstaText

@Composable
fun ErrorScreen(navigateBack: () -> Unit){

    Scaffold() { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            InstaText(
                text = stringResource(R.string.error_screen_title_text),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.size(5.dp))
            InstaText(
                text = stringResource(R.string.error_screen_main_text)
            )
            Spacer(Modifier.size(5.dp))
            InstaButton(
                onClick = {navigateBack()},
                text = stringResource(R.string.error_screen_button_text)
            )
        }
    }
}