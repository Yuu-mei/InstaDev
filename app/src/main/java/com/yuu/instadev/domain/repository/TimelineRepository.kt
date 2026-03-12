package com.yuu.instadev.domain.repository

import com.yuu.instadev.domain.entity.ReelsFirebaseEntity

interface TimelineRepository {
    suspend fun doRetrieveReels(): List<ReelsFirebaseEntity>
}