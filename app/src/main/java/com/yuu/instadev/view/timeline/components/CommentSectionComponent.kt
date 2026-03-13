package com.yuu.instadev.view.timeline.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yuu.instadev.domain.entity.CommentFirebaseEntity

@Composable
fun CommentSectionComponent(
    modifier: Modifier = Modifier,
    comments: List<CommentFirebaseEntity>
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 600.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .size(width = 40.dp, height = 4.dp)
                .background(MaterialTheme.colorScheme.onBackground, RoundedCornerShape(2.dp))
                .align(Alignment.CenterHorizontally)
        )
        LazyColumn() {
            items(comments){ comment ->
                CommentThreadComponent(comment)
            }
        }
    }
}