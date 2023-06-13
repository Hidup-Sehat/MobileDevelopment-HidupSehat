package com.bangkit23.hidupsehat.domain.usecase.food

import com.bangkit23.hidupsehat.data.source.remote.request.AddFoodsRequest
import com.bangkit23.hidupsehat.domain.reporitory.FoodRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodInteractor @Inject constructor(
    private val foodRepository: FoodRepository,
) : FoodUseCase {

    override fun getFoodByName(foodName: String) =
        foodRepository.getFoodByName(foodName)

    override fun searchFoods(query: String) =
        foodRepository.searchFoods(query)

    override fun getFoodsPortionSize(foodName: String) =
        foodRepository.getFoodsPortionSize(foodName)

    override fun saveFoods(addFoodsRequest: AddFoodsRequest) =
        foodRepository.saveFoods(addFoodsRequest)
}