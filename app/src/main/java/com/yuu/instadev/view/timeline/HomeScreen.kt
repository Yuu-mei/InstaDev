package com.yuu.instadev.view.timeline

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yuu.instadev.view.timeline.components.ReelComponent

@Preview
@Composable
fun HomeScreen(){
    val reelsListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = reelsListState
    ) {
        items(25){
            ReelComponent()
            Spacer(Modifier.size(10.dp))
            HorizontalDivider(
                thickness = 8.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}