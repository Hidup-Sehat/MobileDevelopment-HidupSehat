package com.bangkit23.hidupsehat.presentation.screen.manual_foods

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
class ManualFoodsViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ManualFoodsState())
    val state = _state.asStateFlow()

    fun onEvent(event: ManualFoodsEvent) {
        when (event) {
            is ManualFoodsEvent.AddNewFood -> {
                _state.update {
                    val foods = it.foods
                    foods.add(event.food)
                    it.copy(
                        foods = foods
                    )
                }
            }
            is ManualFoodsEvent.ChangePortionSize -> {
                changePortionSize(event.food, event.count)
            }
            is ManualFoodsEvent.DeleteFood -> {
                _state.update {
                    val currentFoods = it.foods
                    val index = currentFoods.indexOfFirst { food -> food?.name == event.food?.name }
                    currentFoods.removeAt(index)
                    it.copy(
                        foods = currentFoods,
                        isDialogEditFoodShow = false
                    )
                }
            }
            is ManualFoodsEvent.EditFood -> {
                _state.update {
                    it.copy(
                        isDialogEditFoodShow = true,
                        foodEdit = event.food
                    )
                }
            }
            is ManualFoodsEvent.HideDialogAddFoods -> {
                _state.update {
                    it.copy(
                        isDialogAddFoodsShow = false
                    )
                }
            }
            is ManualFoodsEvent.HideDialogEditFood -> {
                _state.update {
                    it.copy(
                        isDialogEditFoodShow = false,
                        foodEdit = null
                    )
                }
            }
            is ManualFoodsEvent.HideDropDownPortionSize -> {
                _state.update {
                    it.copy(
                        isPortionSizeDropDownShow = false,
                        portionSizes = emptyList()
                    )
                }
            }
            is ManualFoodsEvent.OnSearchFoodQueryChange -> {
                _state.update {
                    it.copy(
                        foodSearchQuery = event.query
                    )
                }
                searchFoods(event.query)
            }
            is ManualFoodsEvent.SaveAllFoods -> {

            }
            is ManualFoodsEvent.SaveEditedFood -> {
                _state.update {
                    val currentFoods = it.foods
                    val index = currentFoods.indexOfFirst { food -> food?.name == event.food?.name }
                    if (index != -1) {
                        currentFoods[index] = event.food?.copy(
                            energyKKal = getTotalKKal(event.food.energyKKal, event.food.count),
                            count = event.food.count
                        )
                    }
                    it.copy(
                        foods = currentFoods,
                        isDialogEditFoodShow = false,
                    )
                }
            }
            is ManualFoodsEvent.ShowDialogAddFoods -> {
                _state.update {
                    it.copy(
                        isDialogAddFoodsShow = true
                    )
                }
            }
            is ManualFoodsEvent.ShowDropDownPortionSize -> {
                _state.update {
                    it.copy(
                        portionSizes = emptyList()
                    )
                }
                getPortionSizes(event.food)
            }
            is ManualFoodsEvent.OnGetInitialAddFoods -> {
                searchFoods("a")
            }
        }
    }

    private fun getTotalKKal(energyKKal: Double?, count: Int): Double {
        return energyKKal?.times(count) ?: 0.0
    }

    private fun searchFoods(query: String) {
        viewModelScope.launch {
            foodUseCase.searchFoods(query)
                .collect { foods ->
                    _state.update {
                        it.copy(
                            foodSearched = foods,
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
                            portionSizes = foods
                        )
                    }
                }
        }
    }

    private fun changePortionSize(food: Food, count: Int?) {
        _state.update {
            val foodIndex = it.foods.indexOfFirst { mFood -> mFood?.name == food.name }
            if (foodIndex != -1) {
                val updatedFood = food.copy(
                    count = count ?: 1,
                    energyKKal = getTotalKKal(food.energyKKal, count ?: 1)
                )
                it.foods[foodIndex] = updatedFood
            }
            it.copy(
                foods = it.foods
            )
        }
    }
}