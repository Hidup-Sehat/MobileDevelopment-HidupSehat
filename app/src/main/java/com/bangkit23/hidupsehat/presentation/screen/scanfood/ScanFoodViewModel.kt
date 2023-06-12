package com.bangkit23.hidupsehat.presentation.screen.scanfood

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScanFoodViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(ScanFoodState())
    val state = _state.asStateFlow()

    fun onEvent(event: ScanFoodEvent) {
        when (event) {
            is ScanFoodEvent.OnImageFiled -> {
                _state.update {
                    it.copy(
                        imageFile = event.photoFile
                    )
                }
            }
            is ScanFoodEvent.SetLoadingState -> {
                _state.update {
                    it.copy(
                        isLoading = event.isLoading
                    )
                }
            }
            is ScanFoodEvent.OnFlashModeChange -> {
                _state.update {
                    it.copy(
                        isFlashModeOn = event.isFlashOn
                    )
                }
            }
        }
    }
}