package com.bangkit23.hidupsehat.presentation.screen.scanfood_result

import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.presentation.screen.scanfood.model.DetectionResult

sealed class ScanFoodResultEvent {
    data class AddDetectedFoods(val foods: List<DetectionResult>) : ScanFoodResultEvent()
    data class AddNewFood(val food: Food) : ScanFoodResultEvent()
    data class EditFood(val food: Food?) : ScanFoodResultEvent()
    data class SaveAllFoods(val foods: List<Food>) : ScanFoodResultEvent()
    data class OnSearchFoodQueryChange(val query: String) : ScanFoodResultEvent()
    object ShowDialogAddFoods : ScanFoodResultEvent()
    object HideDialogAddFoods : ScanFoodResultEvent()
    object HideDialogEditFood : ScanFoodResultEvent()
    data class ChangePortionSize(val food: Food, val count: Int?) : ScanFoodResultEvent()
    data class ShowDropDownPortionSize(val food: Food) : ScanFoodResultEvent()
    object HideDropDownPortionSize : ScanFoodResultEvent()
    data class DeleteFood(val food: Food?) : ScanFoodResultEvent()
    data class SaveEditedFood(val food: Food?) : ScanFoodResultEvent()
    object OnGetInitialAddFoods : ScanFoodResultEvent()
}