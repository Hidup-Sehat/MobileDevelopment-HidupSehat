package com.bangkit23.hidupsehat.util

import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle
import kotlin.math.pow
import kotlin.math.sqrt

fun calculateSimilarity(actual: PersonBodyAngle, expected: PersonBodyAngle): Double {
    val sumOfSquaredDifferences = (actual.rightElbow - expected.rightElbow).pow(2) +
            (actual.rightHip - expected.rightHip).pow(2) +
            (actual.rightShoulder - expected.rightShoulder).pow(2) +
            (actual.rightKnee - expected.rightKnee).pow(2) +
            (actual.leftElbow - expected.leftElbow).pow(2) +
            (actual.leftShoulder - expected.leftShoulder).pow(2) +
            (actual.leftHip - expected.leftHip).pow(2) +
            (actual.leftKnee - expected.leftKnee).pow(2)

    val euclideanDistance = sqrt(sumOfSquaredDifferences)
    val maxDistance = sqrt(180.0.pow(2) * 8)  // Maximum possible distance

    return 1.0 - (euclideanDistance / maxDistance)
}