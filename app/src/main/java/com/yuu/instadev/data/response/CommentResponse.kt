package com.yuu.instadev.data.response

import com.yuu.instadev.domain.entity.CommentFirebaseEntity
import kotlinx.serialization.Serializable

@Serializable
data class CommentResponse(
    val commentID: String = "",
    val postID: String = "",
    val userID: String = "",
    val parentCommentID: String? = null,
    val text: String = "",
    var likeCount: Int = 0,
    val createdAt: Long = 0L
)

fun CommentResponse.toDomain(): CommentFirebaseEntity{
    return CommentFirebaseEntity(
        commentID = commentID,
        postID = postID,
        parentCommentID = parentCommentID,
        username = userID,
        text = text,
        likeCount = likeCount,
        createdAt = createdAt
    )
}