package com.yuu.instadev.domain.usecase

import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import com.yuu.instadev.domain.repository.TimelineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TimelineObserveReelsUseCase @Inject constructor(private val timelineRepository: TimelineRepository) {
    suspend operator fun invoke(): Flow<List<ReelsFirebaseEntity>>{
        return timelineRepository.doObserveReels()
    }
}