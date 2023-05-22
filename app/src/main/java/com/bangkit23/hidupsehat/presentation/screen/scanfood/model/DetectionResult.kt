package com.bangkit23.hidupsehat.presentation.screen.scanfood.model

import android.graphics.RectF
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectionResult(
    val boundingBox: RectF,
    val text: String,
) : Parcelable
