package com.yuu.instadev.view.timeline.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yuu.instadev.R
import com.yuu.instadev.view.core.components.InstaOutlinedButton
import com.yuu.instadev.view.core.components.InstaText

@Composable
fun CommentComponent(
    commentText: String,
    username: String,
    commentLikeCount: Int,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_profile),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        )
        Spacer(Modifier.width(2.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            InstaText(
                text = username,
                style = MaterialTheme.typography.bodyLarge
            )
            InstaText(
                text = commentText
            )
        }
        Spacer(Modifier.width(2.dp))
        InstaRowIconButton(
            iconId = R.drawable.ic_fav,
            onClick = {},
            contentDescription = "Favorite Button",
            iconText = commentLikeCount.toString(),
            tint = LocalContentColor.current
        )
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_reply),
                contentDescription = "Reply Button",
                tint = LocalContentColor.current
            )
        }
    }
}