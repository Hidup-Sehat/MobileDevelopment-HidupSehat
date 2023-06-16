package com.bangkit23.hidupsehat.domain.usecase.activity

import com.bangkit23.hidupsehat.domain.model.activity.ActivityItem
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface ActivityUseCase {
    fun getYogaActivities(): Flow<Result<List<ActivityItem>>>
    fun getWorkoutActivities(): Flow<Result<List<ActivityItem>>>
    fun updateBurnedCalorie(calorie: Int): Flow<Result<Boolean>>
}