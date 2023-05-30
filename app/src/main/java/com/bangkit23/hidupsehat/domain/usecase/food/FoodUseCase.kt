package com.bangkit23.hidupsehat.domain.usecase.food

import com.bangkit23.hidupsehat.domain.model.food.Food
import kotlinx.coroutines.flow.Flow

interface FoodUseCase {
    fun getFoodByName(foodName: String): Flow<Food>
    fun searchFoods(query: String): Flow<List<Food>>
    fun getFoodsPortionSize(foodName: String): Flow<List<Food>>
}