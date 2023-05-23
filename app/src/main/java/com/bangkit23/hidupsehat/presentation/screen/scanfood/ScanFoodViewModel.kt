package com.bangkit23.hidupsehat.presentation.screen.scanfood

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ScanFoodViewModel @Inject constructor() : ViewModel() {

    var photoFile = mutableStateOf<File?>(null)
        private set

    fun setPhotoFile(file: File?) {
        photoFile.value = file
    }
}