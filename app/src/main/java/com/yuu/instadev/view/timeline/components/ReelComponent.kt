package com.yuu.instadev.view.timeline.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yuu.instadev.R
import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import com.yuu.instadev.view.core.components.InstaText

@Composable
fun ReelComponent(
    modifier: Modifier = Modifier,
    reel: ReelsFirebaseEntity,
    isReelLiked: Boolean,
    likeReel: (String) -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(){
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Reel test",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(10.dp))
                InstaText(text = reel.username, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            //Fav Icon
            InstaRowIconButton(
                iconId = R.drawable.ic_fav,
                onClick = { likeReel(reel.postID) },
                contentDescription = "Favorite Button",
                iconText = reel.likeCount.toString(),
                tint = if(isReelLiked) Color.Red else LocalContentColor.current
            )
            //Comments Icon
            InstaRowIconButton(
                iconId = R.drawable.ic_comments,
                onClick = {},
                contentDescription = "Favorite Button",
                iconText = reel.commentCount.toString()
            )
        }
        InstaText(
            text = reel.text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}