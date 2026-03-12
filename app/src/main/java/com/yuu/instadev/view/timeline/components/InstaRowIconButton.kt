package com.yuu.instadev.view.timeline.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yuu.instadev.view.core.components.InstaText

@Composable
fun InstaRowIconButton(
    modifier: Modifier = Modifier,
    iconId: Int = 0,
    onClick: () -> Unit = {},
    iconText: String = "",
    contentDescription: String = "",
    tint: Color = LocalContentColor.current
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = contentDescription,
                tint = tint
            )
        }
        Spacer(Modifier.width(8.dp))
        InstaText(text = iconText)
    }
}