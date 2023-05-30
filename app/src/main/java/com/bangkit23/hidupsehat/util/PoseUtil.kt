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

fun calculateCosineSimilarity(actual: PersonBodyAngle, expected: PersonBodyAngle): Double {
    val dotProduct = (actual.rightElbow * expected.rightElbow) +
            (actual.rightShoulder * expected.rightShoulder) +
            (actual.rightHip * expected.rightHip) +
            (actual.rightKnee * expected.rightKnee) +
            (actual.leftElbow * expected.leftElbow) +
            (actual.leftShoulder * expected.leftShoulder) +
            (actual.leftHip * expected.leftHip) +
            (actual.leftKnee * expected.leftKnee)

    val actualMagnitude = sqrt(
        actual.rightElbow.pow(2) +
                actual.rightShoulder.pow(2) +
                actual.rightHip.pow(2) +
                actual.rightKnee.pow(2) +
                actual.leftElbow.pow(2) +
                actual.leftShoulder.pow(2) +
                actual.leftHip.pow(2) +
                actual.leftKnee.pow(2)
    )

    val expectedMagnitude = sqrt(
        expected.rightElbow.pow(2) +
                expected.rightShoulder.pow(2) +
                expected.rightHip.pow(2) +
                expected.rightKnee.pow(2) +
                expected.leftElbow.pow(2) +
                expected.leftShoulder.pow(2) +
                expected.leftHip.pow(2) +
                expected.leftKnee.pow(2)
    )

    return dotProduct / (actualMagnitude * expectedMagnitude)
}