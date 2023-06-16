package com.bangkit23.hidupsehat.domain.reporitory

import com.bangkit23.hidupsehat.domain.model.food.FoodHistoryDetailItem
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface MonitoringRepository {
    fun getFoodsHistory(date: String): Flow<Result<FoodHistoryDetailItem>>
}