package com.yuu.instadev.domain.usecase

import com.yuu.instadev.domain.repository.TimelineRepository
import javax.inject.Inject

class TimelineGetLikedReelsUseCase @Inject constructor(private val timelineRepository: TimelineRepository) {
    suspend operator fun invoke(): Map<String, Boolean>{
        return timelineRepository.doGetlikedReels()
    }
}