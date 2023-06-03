package com.bangkit23.hidupsehat.presentation.screen.scanfood_result

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.bangkit23.hidupsehat.domain.model.food.Food

data class ScanFoodResultState(
    val isLoading: Boolean = false,
    val isDialogAddFoodsShow: Boolean = false,
    val isDialogEditFoodShow: Boolean = false,
    val isPortionSizeDropDownShow: Boolean = false,
    val foodSearchQuery: String = "",
    val foodEdit: Food? = null,
    val portionSizes: List<Food> = emptyList(),
    val foodSearched: List<Food> = emptyList(),
    val foods: SnapshotStateList<Food?> = mutableStateListOf(),
)

