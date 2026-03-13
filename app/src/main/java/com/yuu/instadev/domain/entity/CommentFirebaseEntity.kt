package com.yuu.instadev.domain.entity

data class CommentFirebaseEntity(
    val commentID: String = "",
    val postID: String = "",
    val username: String = "",
    val parentCommentID: String? = null,
    val text: String = "",
    var likeCount: Int = 0,
    val createdAt: Long = 0L,
    val replies: List<CommentFirebaseEntity> = emptyList()
)