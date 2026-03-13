package com.yuu.instadev.domain.usecase

import com.yuu.instadev.domain.entity.CommentFirebaseEntity
import com.yuu.instadev.domain.repository.TimelineRepository
import javax.inject.Inject

class TimelineGetReelCommentsUseCase @Inject constructor(private val timelineRepository: TimelineRepository) {
    suspend operator fun invoke(reelID: String): List<CommentFirebaseEntity>{
        return timelineRepository.doGetReelComments(reelID)
    }
}