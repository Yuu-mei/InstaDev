package com.yuu.instadev.domain.usecase

import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import com.yuu.instadev.domain.repository.TimelineRepository
import javax.inject.Inject

class TimelineHomeUseCase @Inject constructor(private val timelineRepository: TimelineRepository) {
    suspend operator fun invoke(): List<ReelsFirebaseEntity>{
        return timelineRepository.doRetrieveReels()
    }
}