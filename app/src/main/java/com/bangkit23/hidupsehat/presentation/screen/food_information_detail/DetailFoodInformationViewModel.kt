package com.bangkit23.hidupsehat.presentation.screen.food_information_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFoodInformationViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailFoodInformationState())
    val state = _state.asStateFlow()

    fun onEvent(event : DetailFoodInformationEvent){
        when(event){
            is DetailFoodInformationEvent.OnGetFoodById -> {
                getFoodByName(event.name)
            }
            is DetailFoodInformationEvent.OnDropDownItemClick -> {
                _state.update {
                    it.copy(
                        count = 1
                    )
                }
                changePortionSize(event.food, event.count)
            }
            is DetailFoodInformationEvent.OnCountChange -> {
                _state.update {
                    it.copy(
                        count = event.count
                    )
                }
                changeFoodCount(event.count)
            }
            is DetailFoodInformationEvent.OnPortionSizeClick -> {
                _state.update {
                    it.copy(
                        portionSize = emptyList(),
                    )
                }
                getPortionSizes(event.food)
            }
        }
    }

    private fun getFoodByName(name : String) {
        viewModelScope.launch {
            foodUseCase.getFoodByName(name)
                .collect {food ->
                    _state.update {
                        it.copy(
                            food = food,
                            notEditedFood = food
                        )
                    }
                }
        }
    }
    private fun getPortionSizes(food: Food) {
        viewModelScope.launch {
            foodUseCase.getFoodsPortionSize(food.name.toString())
                .collect { foods ->
                    _state.update {
                        it.copy(
                            portionSize = foods
                        )
                    }
                }
        }
    }

    private fun changePortionSize(food: Food, count: Int) {
        _state.update {
            val updatedFood = food.copy(
                count = count,
                energyKKal = food.energyKKal?.times(count),
            )
            it.copy(
                food = updatedFood
            )
        }
    }

    private fun changeFoodCount(count: Int) {
        _state.update {
            val notEditedFood = it.notEditedFood
            val updatedFood =it.food?.copy(
                count = count,
                energyKKal = notEditedFood?.energyKKal?.times(count),
                energyKj = notEditedFood?.energyKj?.times(count),
                sugar = notEditedFood?.sugar?.times(count),
                potassium = notEditedFood?.potassium?.times(count),
                carbohydrate = notEditedFood?.carbohydrate?.times(count),
                cholesterol = notEditedFood?.cholesterol?.times(count),
                fat = notEditedFood?.fat?.times(count),
                saturatedFat = notEditedFood?.saturatedFat?.times(count),
                transFat = notEditedFood?.transFat?.times(count),
                polyunsaturatedFat = notEditedFood?.polyunsaturatedFat?.times(count),
                monounsaturatedFat = notEditedFood?.monounsaturatedFat?.times(count),
                protein = notEditedFood?.protein?.times(count),
                fiber = notEditedFood?.fiber?.times(count),
                sodium = notEditedFood?.sodium?.times(count),
            )
            it.copy(
                food = updatedFood
            )
        }
    }
}