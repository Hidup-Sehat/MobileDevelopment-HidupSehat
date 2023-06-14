package com.bangkit23.hidupsehat.presentation.screen.scanfood_result

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.data.source.remote.request.AddFoodsRequest
import com.bangkit23.hidupsehat.data.source.remote.request.FoodRequestItem
import com.bangkit23.hidupsehat.domain.model.food.Food
import com.bangkit23.hidupsehat.domain.usecase.food.FoodUseCase
import com.bangkit23.hidupsehat.presentation.screen.scanfood.model.DetectionResult
import com.bangkit23.hidupsehat.util.DateHelper
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

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
                saveAllFoods(event.foods)
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
                changePortionSize(event.food, event.count)
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
                        portionSizes = emptyList()
                    )
                }
                getPortionSizes(event.food)
            }
            is ScanFoodResultEvent.DeleteFood -> {
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
            is ScanFoodResultEvent.SaveEditedFood -> {
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
            is ScanFoodResultEvent.OnGetInitialAddFoods -> {
                searchFoods("a")
            }
        }
    }

    private fun getFoods(foods: List<DetectionResult>) {
        viewModelScope.launch {
            val result = foods.groupingBy { it.text }
                .eachCount()
                .map { (label, count) ->
                    val food = foodUseCase.getFoodByName(label.trim()).firstOrNull()
                    food?.copy(
                        count = count,
                        energyKKal = getTotalKKal(food.energyKKal, count),
                    )
                }
            _state.update {
                it.copy(
                    foods = result.toMutableStateList()
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

    private fun saveAllFoods(foods: List<Food?>) = viewModelScope.launch {
        val request = foods.map {
            FoodRequestItem(
                portionSize = "${it?.count} x/ ${it?.portionSize}",
                id = it?.id,
                foodName = it?.name
            )
        }
        val foodRequest = AddFoodsRequest(
            date = DateHelper.getCurrentDate(),
            lastUpdated = DateHelper.getCurrentDate(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
            totalCarb = foods.sumOf { it?.carbohydrate?.toInt() ?: 0 },
            totalProtein = foods.sumOf { it?.protein?.toInt() ?: 0 },
            totalFat = foods.sumOf { it?.fat?.toInt() ?: 0 },
            id = "${Random.nextInt(100)}",
            totalFiber = foods.sumOf { it?.fiber?.toInt() ?: 0 },
            foods = request
        )
        foodUseCase.saveFoods(foodRequest).collect { result ->
            when (result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }
}