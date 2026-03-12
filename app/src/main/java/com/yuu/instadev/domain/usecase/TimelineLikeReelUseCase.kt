package com.yuu.instadev.domain.usecase

import com.yuu.instadev.domain.repository.TimelineRepository
import javax.inject.Inject

class TimelineLikeReelUseCase @Inject constructor(private val timelineRepository: TimelineRepository) {
    suspend operator fun invoke(reelID: String): Boolean{
        return timelineRepository.doLikeReel(reelID)
    }
}