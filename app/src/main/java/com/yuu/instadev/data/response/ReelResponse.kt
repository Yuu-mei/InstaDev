package com.yuu.instadev.data.response

import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import kotlinx.serialization.Serializable

@Serializable
data class ReelResponse(
    val postID: String = "",
    val userID: String = "",
    val mediaURL: String = "",
    val mediaType: MediaType = MediaType.IMAGE,
    val text: String = "",
    val commentCount: Int = 0,
    val likeCount: Int = 0,
    val createdAt: Long = 0L
)

enum class MediaType{
    IMAGE, VIDEO
}

fun ReelResponse.toDomain(): ReelsFirebaseEntity{
    return ReelsFirebaseEntity(
        postID = postID,
        username = userID,
        mediaURL = mediaURL,
        mediaType = mediaType,
        text = text,
        commentCount = commentCount,
        likeCount = likeCount,
        createdAt = createdAt
    )
}