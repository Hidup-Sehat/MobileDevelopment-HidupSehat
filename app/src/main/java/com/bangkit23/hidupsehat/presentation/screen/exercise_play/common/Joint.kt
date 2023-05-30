package com.bangkit23.hidupsehat.presentation.screen.exercise_play.common

import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.ml.BodyPart

object Joint {
    val RIGHT_ELBOW = Triple(BodyPart.RIGHT_WRIST, BodyPart.RIGHT_ELBOW, BodyPart.RIGHT_SHOULDER)
    val LEFT_ELBOW = Triple(BodyPart.LEFT_WRIST, BodyPart.LEFT_ELBOW, BodyPart.LEFT_SHOULDER)
    val RIGHT_SHOULDER = Triple(BodyPart.RIGHT_ELBOW, BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_HIP)
    val LEFT_SHOULDER = Triple(BodyPart.LEFT_ELBOW, BodyPart.LEFT_SHOULDER, BodyPart.LEFT_HIP)
    val RIGHT_HIP = Triple(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_HIP, BodyPart.RIGHT_KNEE)
    val LEFT_HIP = Triple(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_HIP, BodyPart.LEFT_KNEE)
    val RIGHT_KNEE = Triple(BodyPart.RIGHT_HIP, BodyPart.RIGHT_KNEE, BodyPart.RIGHT_ANKLE)
    val LEFT_KNEE = Triple(BodyPart.LEFT_HIP, BodyPart.LEFT_KNEE, BodyPart.LEFT_ANKLE)
}