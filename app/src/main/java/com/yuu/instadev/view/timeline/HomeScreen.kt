@file:OptIn(ExperimentalMaterial3Api::class)

package com.yuu.instadev.view.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yuu.instadev.view.timeline.components.CommentSectionComponent
import com.yuu.instadev.view.timeline.components.ReelComponent

@Preview
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()){
    val uiState: HomeUIState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val reelsListState = rememberLazyListState()
    var showComments by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = reelsListState
        ) {
            items(uiState.reels){ reel ->
                ReelComponent(
                    reel = reel,
                    isReelLiked = uiState.likedReels[reel.postID] ?: false,
                    likeReel = { homeViewModel.likeReel(reel.postID) },
                    openCommentSection = {
                        showComments = true
                        homeViewModel.showReelComments(reel.postID)
                    }
                )
                Spacer(Modifier.size(10.dp))
                HorizontalDivider(
                    thickness = 8.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        if(showComments){
            ModalBottomSheet(
                onDismissRequest = {
                    showComments = false
                },
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ) {
                CommentSectionComponent(
                    comments = uiState.reelComments
                )
            }
        }
    }
}