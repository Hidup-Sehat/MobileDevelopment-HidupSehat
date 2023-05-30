package com.bangkit23.hidupsehat.presentation.screen.scanfood_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
import com.bangkit23.hidupsehat.presentation.screen.scanfood.model.DetectionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanFoodResultViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ScanFoodResultState())
    val state = _state.asStateFlow()

    fun onEvent(event: ScanFoodResultEvent) {
        when (event) {
            is ScanFoodResultEvent.AddDetectedFoods -> {
                getFoods(event.foods)
            }
            is ScanFoodResultEvent.AddNewFood -> {
                _state.update {
                    val foods = it.foods
                    foods.add(event.food)
                    it.copy(
                        foods = foods
                    )
                }
            }
            is ScanFoodResultEvent.EditFood -> {
                _state.update {
                    it.copy(
                        isDialogEditFoodShow = true,
                        foodEdit = event.food
                    )
                }
            }
            is ScanFoodResultEvent.HideDialogEditFood -> {
                _state.update {
                    it.copy(
                        isDialogEditFoodShow = false,
                        foodEdit = null
                    )
                }
            }
            is ScanFoodResultEvent.SaveAllFoods -> {

            }
            is ScanFoodResultEvent.OnSearchFoodQueryChange -> {
                _state.update {
                    it.copy(
                        foodSearchQuery = event.query
                    )
                }
                searchFoods(event.query)
            }
            is ScanFoodResultEvent.ShowDialogAddFoods -> {
                _state.update {
                    it.copy(
                        isDialogAddFoodsShow = true
                    )
                }
            }
            is ScanFoodResultEvent.HideDialogAddFoods -> {
                _state.update {
                    it.copy(
                        isDialogAddFoodsShow = false
                    )
                }
            }
            is ScanFoodResultEvent.ChangePortionSize -> {
                _state.update {
                    it.copy(
                        isPortionSizeDropDownShow = false,
                    )
                }
                changePortionSize(event.food)
            }
            is ScanFoodResultEvent.HideDropDownPortionSize -> {
                _state.update {
                    it.copy(
                        isPortionSizeDropDownShow = false,
                        portionSizes = emptyList()
                    )
                }
            }
            is ScanFoodResultEvent.ShowDropDownPortionSize -> {
                _state.update {
                    it.copy(
                        isPortionSizeDropDownShow = true,
                        portionSizes = emptyList()
                    )
                }
                getPortionSizes(event.food)
            }
        }
    }

    private fun getFoods(foods: List<DetectionResult>) {
        viewModelScope.launch {
            val result = foods.groupingBy { it.text }
                .eachCount()
                .map { (label, count) ->
                    val food = foodUseCase.getFoodByName(label).firstOrNull()
                    food?.copy(
                        count = count,
                        energyKKal = getTotalKKal(food.energyKKal, count)
                    )
                }

            _state.update {
                it.copy(
                    foods = result.toMutableList()
                )
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

    private fun changePortionSize(food: Food) {
        _state.update {
            val foodIndex = it.foods.indexOfFirst { mFood -> mFood?.name == food.name }
            if (foodIndex != -1) {
                it.foods[foodIndex] = food
            }
            it.copy(
                foods = it.foods
            )
        }
    }
}