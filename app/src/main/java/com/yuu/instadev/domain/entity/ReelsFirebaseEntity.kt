package com.yuu.instadev.domain.entity

import com.yuu.instadev.data.response.MediaType

data class ReelsFirebaseEntity(
    val postID: String = "",
    val username: String = "",
    val mediaURL: String = "",
    val mediaType: MediaType = MediaType.IMAGE,
    val text: String = "",
    val commentCount: Number = 0,
    val likeCount: Number = 0,
    val createdAt: Long = 0L
)