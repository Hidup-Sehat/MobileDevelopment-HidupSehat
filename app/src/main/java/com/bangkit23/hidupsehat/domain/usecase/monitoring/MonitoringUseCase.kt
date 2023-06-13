package com.bangkit23.hidupsehat.domain.usecase.monitoring

import com.bangkit23.hidupsehat.domain.model.food.FoodHistoryDetailItem
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface MonitoringUseCase {
    fun getFoodsHistory(): Flow<Result<List<FoodHistoryDetailItem>>>
}