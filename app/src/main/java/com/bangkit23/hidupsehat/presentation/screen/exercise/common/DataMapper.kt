package com.bangkit23.hidupsehat.presentation.screen.exercise.common

import com.bangkit23.hidupsehat.domain.model.activity.ActivityItem
import com.bangkit23.hidupsehat.domain.model.activity.MovementItem
import com.bangkit23.hidupsehat.domain.model.activity.BodyAngle
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Pose
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle

fun ActivityItem.toExercise() = Exercise(
    id = "$id",
    title = "$difficulty",
    description = "$category",
    image = "$imgUrl",
    cardColor = cardColor ?: 0,
    caloriesBurned = caloriesBurned ?: 0,
    poses = movementList.map(MovementItem::toPose),
)

fun MovementItem.toPose() = Pose(
    image = "$imgUrl",
    title = "$movementName",
    detail = "$movementDesc",
    personBodyAngle = movementData.toPersonBodyAngle(),
)

fun BodyAngle.toPersonBodyAngle() = PersonBodyAngle(
    rightElbow = sudutSikuKanan.toDouble(),
    leftElbow = sudutSikuKiri.toDouble(),
    rightShoulder = sudutKetiakKanan.toDouble(),
    leftShoulder = sudutKetiakKiri.toDouble(),
    rightHip = sudutPinggulKanan.toDouble(),
    leftHip = sudutPinggulKiri.toDouble(),
    rightKnee = sudutLututKanan.toDouble(),
    leftKnee = sudutLututKiri.toDouble(),
)
