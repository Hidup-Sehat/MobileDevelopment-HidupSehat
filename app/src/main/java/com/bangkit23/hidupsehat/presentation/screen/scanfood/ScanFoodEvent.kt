package com.bangkit23.hidupsehat.presentation.screen.scanfood

import java.io.File

sealed class ScanFoodEvent {
    data class SetLoadingState(val isLoading: Boolean) : ScanFoodEvent()
    data class OnImageFiled(val photoFile: File) : ScanFoodEvent()
    data class OnFlashModeChange(val isFlashOn: Boolean) : ScanFoodEvent()
}