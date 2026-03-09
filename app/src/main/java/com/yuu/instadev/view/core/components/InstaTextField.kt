package com.yuu.instadev.view.core.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun InstaTextField(
    modifier: Modifier = Modifier,
    value: String,
    singleLine: Boolean = true,
    onValueChanged: (String) -> Unit,
    shape: Shape = MaterialTheme.shapes.large,
    label: String = "",
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        modifier = modifier,
        label = {
            InstaText(text = label)
        },
        value = value,
        onValueChange = { onValueChanged(it) },
        keyboardOptions = keyboardOptions,
        shape = shape,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}