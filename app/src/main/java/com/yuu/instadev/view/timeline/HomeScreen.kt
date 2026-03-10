package com.yuu.instadev.view.timeline

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.yuu.instadev.R
import com.yuu.instadev.view.core.components.InstaText

@Preview
@Composable
fun HomeScreen(){
    val reelsListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = reelsListState
    ) {
        items(25){
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Reel test",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            InstaText(text = "Example text $it", modifier = Modifier.fillMaxWidth())
        }
    }
}