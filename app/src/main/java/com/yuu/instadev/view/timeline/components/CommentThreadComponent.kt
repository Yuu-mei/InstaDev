package com.yuu.instadev.view.timeline.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yuu.instadev.domain.entity.CommentFirebaseEntity

@Composable
fun CommentThreadComponent(
    comment: CommentFirebaseEntity,
    depth: Int = 0
){
    Column(
        modifier = Modifier
            .padding(start = (depth*10).dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {
        CommentComponent(
            commentText = comment.text,
            username = comment.username,
            commentLikeCount = comment.likeCount
        )

        comment.replies.forEach { reply ->
            CommentThreadComponent(
                comment = reply,
                depth = depth+1
            )
        }
    }
}