package com.yuu.instadev.view.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun InstaOutlinedButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    border: BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    text: String,
    titleColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    OutlinedButton(
        modifier = modifier,
        colors = colors,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        border = border
    ) {
        InstaText(
            modifier = Modifier.padding(4.dp),
            text = text,
            color = titleColor
        )
    }
}