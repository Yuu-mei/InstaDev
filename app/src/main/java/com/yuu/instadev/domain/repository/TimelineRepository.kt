package com.yuu.instadev.domain.repository

import com.yuu.instadev.domain.entity.CommentFirebaseEntity
import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

interface TimelineRepository {
    suspend fun doRetrieveReels(): List<ReelsFirebaseEntity>
    suspend fun doLikeReel(reelID: String) : Boolean
    suspend fun doGetlikedReels(): Map<String, Boolean>
    suspend fun doObserveReels(): Flow<List<ReelsFirebaseEntity>> = callbackFlow {}
    suspend fun doGetReelComments(reelID: String): List<CommentFirebaseEntity>
}