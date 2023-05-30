package com.bangkit23.hidupsehat.presentation.screen.exercise.yoga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Exercise
import com.bangkit23.hidupsehat.presentation.screen.exercise.model.Pose
import com.bangkit23.hidupsehat.presentation.screen.exercise_play.model.PersonBodyAngle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YogaViewModel @Inject constructor() : ViewModel() {

    private var _state = MutableStateFlow(YogaState())
    val state = _state.asStateFlow()

    private val list = listOf(
        Exercise(
            title = "Pemula 1",
            description = "6 Gerakan",
            image = "https://lh4.googleusercontent.com/B_uvjZ9jocUPr-z7vjDZ_8z2DrneGzUmo01opvuawShAqZneya90dxboEdb5zsl5DIEkeFawl-yOIR2IEAdfWVUYIuCCMRvn3VkJlKIBLTKAEp-eDWAvQxLbeqPE6Tu04Q=w1280",
            poses = listOf(
                Pose(
                    image = "https://youaligned.com/wp-content/uploads/2018/03/11-Energy-Boosting-Yoga-Poses.jpg",
                    title = "Gerakan 1: (Goddess)",
                    detail = "Menjaga tubuh",
                    personBodyAngle = PersonBodyAngle(
                        rightElbow = 170.0,
                        rightShoulder = 170.0,
                        rightHip = 135.0,
                        rightKnee = 180.0,
                        leftElbow = 170.0,
                        leftShoulder = 170.0,
                        leftHip = 90.0,
                        leftKnee = 90.0
                    )
                ),
                Pose(
                    image = "https://lh5.googleusercontent.com/rQeHX2p9QjIE5D2uvXc1sjWHUdQ4pE3MfJ0U0cmZnAKb1WLGSIo7EDYko8OhYpJnjK1g0gyvJJzbjsA13miflrkB4fAaP751_V6DSJLl9m4S7z4hcd0Od0XRn5q9F5aqLw=w1280",
                    title = "Gerakan 2: (Yoga)",
                    detail = "Menjaga tubuh",
                    personBodyAngle = PersonBodyAngle(
                        rightElbow = 180.0,
                        rightShoulder = 180.0,
                        rightHip = 180.0,
                        rightKnee = 180.0,
                        leftElbow = 180.0,
                        leftShoulder = 180.0,
                        leftHip = 90.0,
                        leftKnee = 180.0
                    )
                ),
            )
        ),
        Exercise(
            title = "Warming Up in 6m",
            description = "4 Gerakan",
            image = "https://lh5.googleusercontent.com/rQeHX2p9QjIE5D2uvXc1sjWHUdQ4pE3MfJ0U0cmZnAKb1WLGSIo7EDYko8OhYpJnjK1g0gyvJJzbjsA13miflrkB4fAaP751_V6DSJLl9m4S7z4hcd0Od0XRn5q9F5aqLw=w1280",
            poses = listOf(
                Pose(
                    image = "https://lh5.googleusercontent.com/rQeHX2p9QjIE5D2uvXc1sjWHUdQ4pE3MfJ0U0cmZnAKb1WLGSIo7EDYko8OhYpJnjK1g0gyvJJzbjsA13miflrkB4fAaP751_V6DSJLl9m4S7z4hcd0Od0XRn5q9F5aqLw=w1280",
                    title = "Gerakan 1: (Goddess)",
                    detail = "Menjaga tubuh",
                    personBodyAngle = PersonBodyAngle(
                        rightElbow = 45.0,
                        rightShoulder = 45.0,
                        rightHip = 100.0,
                        rightKnee = 100.0,
                        leftElbow = 45.0,
                        leftShoulder = 45.0,
                        leftHip = 100.0,
                        leftKnee = 100.0
                    )
                )
            )
        ),
        Exercise(
            title = "Warming Up in 9m",
            description = "4 Gerakan",
            image = "https://lh5.googleusercontent.com/rQeHX2p9QjIE5D2uvXc1sjWHUdQ4pE3MfJ0U0cmZnAKb1WLGSIo7EDYko8OhYpJnjK1g0gyvJJzbjsA13miflrkB4fAaP751_V6DSJLl9m4S7z4hcd0Od0XRn5q9F5aqLw=w1280",
            poses = listOf(
                Pose(
                    image = "https://lh5.googleusercontent.com/rQeHX2p9QjIE5D2uvXc1sjWHUdQ4pE3MfJ0U0cmZnAKb1WLGSIo7EDYko8OhYpJnjK1g0gyvJJzbjsA13miflrkB4fAaP751_V6DSJLl9m4S7z4hcd0Od0XRn5q9F5aqLw=w1280",
                    title = "Gerakan 1: (Goddess)",
                    detail = "Menjaga tubuh",
                    personBodyAngle = PersonBodyAngle(
                        rightElbow = 45.0,
                        rightShoulder = 45.0,
                        rightHip = 100.0,
                        rightKnee = 100.0,
                        leftElbow = 45.0,
                        leftShoulder = 45.0,
                        leftHip = 100.0,
                        leftKnee = 100.0
                    )
                )
            )
        ),
    )

    fun onEvent(event: YogaEvent) {
        when (event) {
            is YogaEvent.Refresh -> {
                getListExercises()
            }
            is YogaEvent.OpenSheet -> {
                _state.update {
                    it.copy(
                        openBottomSheet = true,
                        clickedExercise = event.exercise
                    )
                }
            }
            is YogaEvent.DismissSheet -> {
                _state.update {
                    it.copy(
                        openBottomSheet = false
                    )
                }
            }
        }
    }

    private fun getListExercises() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    lastActivities = list,
                    flexibilityExercises = list,
                    strengthExercises = list,
                    recoveryExercises = list
                )
            }
        }
    }
}