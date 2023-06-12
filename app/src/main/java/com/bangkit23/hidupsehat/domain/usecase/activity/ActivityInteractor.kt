package com.bangkit23.hidupsehat.domain.usecase.activity

import com.bangkit23.hidupsehat.domain.reporitory.ActivityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityInteractor @Inject constructor(
    private val activityRepository: ActivityRepository,
) : ActivityUseCase {

    override fun getYogaActivities() = activityRepository.getYogaActivities()

    override fun getWorkoutActivities() = activityRepository.getWorkoutActivities()
}