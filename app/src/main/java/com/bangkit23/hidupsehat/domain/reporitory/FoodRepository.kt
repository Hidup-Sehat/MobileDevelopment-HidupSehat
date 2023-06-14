package com.bangkit23.hidupsehat.domain.reporitory

import com.bangkit23.hidupsehat.domain.model.food.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getFoodByName(foodName: String): Flow<Food>
    fun searchFoods(query: String): Flow<List<Food>>
    fun getFoodsPortionSize(foodName: String): Flow<List<Food>>
    fun getAllFoods() : Flow<List<Food>>
}