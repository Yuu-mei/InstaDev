package com.yuu.instadev.view.core.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun InstaTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    label: String = "",
    keyboardOptions: KeyboardOptions
) {
    OutlinedTextField(
        modifier = modifier,
        label = {
            InstaText(text = label)
        },
        value = value,
        onValueChange = { onValueChanged(it) },
        keyboardOptions = keyboardOptions,
        shape = shape
    )
}