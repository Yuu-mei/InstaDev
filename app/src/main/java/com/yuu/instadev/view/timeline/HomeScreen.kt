package com.yuu.instadev.view.timeline

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuu.instadev.view.timeline.components.ReelComponent

@Preview
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()){
    val uiState: HomeUIState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val reelsListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = reelsListState
    ) {
        items(uiState.reels){ reel ->
            ReelComponent(reel = reel)
            Spacer(Modifier.size(10.dp))
            HorizontalDivider(
                thickness = 8.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}