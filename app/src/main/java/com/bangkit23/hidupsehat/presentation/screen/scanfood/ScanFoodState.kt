package com.bangkit23.hidupsehat.presentation.screen.scanfood

import java.io.File

data class ScanFoodState(
    val isLoading: Boolean = false,
    val imageFile: File? = null,
    val isFlashModeOn: Boolean = false,
)