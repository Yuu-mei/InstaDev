package com.yuu.instadev.view.timeline.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yuu.instadev.R
import com.yuu.instadev.view.core.components.InstaText

@Preview
@Composable
fun ReelComponent(
    modifier: Modifier = Modifier
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
                InstaText(text = "Username", style = MaterialTheme.typography.bodyLarge)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            //Fav Icon
            InstaRowIconButton(
                iconId = R.drawable.ic_fav,
                onClick = {},
                contentDescription = "Favorite Button",
                iconText = "100"
            )
            //Comments Icon
            InstaRowIconButton(
                iconId = R.drawable.ic_comments,
                onClick = {},
                contentDescription = "Favorite Button",
                iconText = "20"
            )
        }
        InstaText(
            text = "Example text",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}