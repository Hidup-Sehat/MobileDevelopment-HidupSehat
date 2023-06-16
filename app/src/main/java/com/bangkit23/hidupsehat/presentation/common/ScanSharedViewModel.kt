package com.bangkit23.hidupsehat.presentation.common

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.bangkit23.hidupsehat.presentation.screen.scanfood.model.DetectionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ScanSharedViewModel @Inject constructor() : ViewModel() {

    private val _bitmap = MutableStateFlow<Bitmap?>(null)
    val bitmap = _bitmap.asStateFlow()

    private val _scanResults = MutableStateFlow<List<DetectionResult>>(emptyList())
    val scanResults = _scanResults.asStateFlow()

    fun setBitmap(bitmap: Bitmap?) {
        _bitmap.value = bitmap
    }

    fun setScanResults(scanResults: List<DetectionResult>) {
        _scanResults.value = scanResults
    }
}