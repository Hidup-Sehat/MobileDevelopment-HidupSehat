package com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.ml

import android.graphics.PointF

data class KeyPoint(val bodyPart: BodyPart, var coordinate: PointF, val score: Float)
