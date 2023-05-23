package com.bangkit23.hidupsehat.presentation.screen.scanfood_result

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bangkit23.hidupsehat.presentation.model.Food
import com.bangkit23.hidupsehat.presentation.screen.scanfood.model.DetectionResult
import com.bangkit23.hidupsehat.util.getCalories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanFoodResultViewModel @Inject constructor() : ViewModel() {

    var scanResults = mutableStateOf<List<Food>>(emptyList())
        private set

    fun addAllScanResults(detectionResult: List<DetectionResult>) {
        val result = detectionResult.groupingBy { it.text }
            .eachCount()
            .map { (label, count) ->
                Food(label, count, label.getCalories(count))
            }
        scanResults.value = result
    }
}