package com.bangkit23.hidupsehat.presentation.screen.exercise_play.common

import android.graphics.Bitmap
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.ml.Person

interface PoseDetector : AutoCloseable {

    fun estimateSinglePose(bitmap: Bitmap): Person

    fun lastInferenceTimeNanos(): Long
}
