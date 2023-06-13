package com.bangkit23.hidupsehat.presentation.screen.manual_foods

import com.bangkit23.hidupsehat.domain.model.food.Food

sealed class ManualFoodsEvent {
    data class AddNewFood(val food: Food) : ManualFoodsEvent()
    data class EditFood(val food: Food?) : ManualFoodsEvent()
    data class SaveAllFoods(val foods: List<Food>) : ManualFoodsEvent()
    data class OnSearchFoodQueryChange(val query: String) : ManualFoodsEvent()
    object ShowDialogAddFoods : ManualFoodsEvent()
    object HideDialogAddFoods : ManualFoodsEvent()
    object HideDialogEditFood : ManualFoodsEvent()
    data class ChangePortionSize(val food: Food, val count: Int?) : ManualFoodsEvent()
    data class ShowDropDownPortionSize(val food: Food) : ManualFoodsEvent()
    object HideDropDownPortionSize : ManualFoodsEvent()
    object OnGetInitialAddFoods : ManualFoodsEvent()
    data class DeleteFood(val food: Food?) : ManualFoodsEvent()
    data class SaveEditedFood(val food: Food?) : ManualFoodsEvent()
}