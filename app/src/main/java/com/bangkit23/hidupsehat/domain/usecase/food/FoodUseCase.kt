package com.bangkit23.hidupsehat.domain.usecase.food

import com.bangkit23.hidupsehat.data.source.remote.request.AddFoodsRequest
import com.bangkit23.hidupsehat.domain.model.food.AddFoods
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.util.Result
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {
    fun getFoodByName(foodName: String): Flow<Food>
    fun searchFoods(query: String): Flow<List<Food>>
    fun getFoodsPortionSize(foodName: String): Flow<List<Food>>
    fun getAllFoods() : Flow<List<Food>>
    fun saveFoods(addFoodsRequest: AddFoodsRequest): Flow<Result<AddFoods>>
}