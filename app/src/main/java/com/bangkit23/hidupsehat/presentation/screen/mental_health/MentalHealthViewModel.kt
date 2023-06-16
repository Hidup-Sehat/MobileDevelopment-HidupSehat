package com.bangkit23.hidupsehat.presentation.screen.mental_health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.model.activity.ActivityItem
import com.bangkit23.hidupsehat.domain.usecase.activity.ActivityUseCase
import com.bangkit23.hidupsehat.domain.usecase.auth.AuthUseCase
import com.bangkit23.hidupsehat.domain.usecase.feed.FeedUseCase
import com.bangkit23.hidupsehat.presentation.screen.exercise.common.toExercise
import com.bangkit23.hidupsehat.presentation.screen.mental_health.model.Consultation
import com.bangkit23.hidupsehat.util.DateHelper.getGreetingMessage
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class MentalHealthViewModel @Inject constructor(
    private val activityUseCase: ActivityUseCase,
    private val authUseCase: AuthUseCase,
    private val feedUseCase: FeedUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MentalHealthState())
    val state = _state.asStateFlow()

    init {
        getUserGreetingMessage()
        getRecommendationActivities()
        getRecommendationFeeds()
        getRecommendationConsultations()
    }

    private fun getUserGreetingMessage() = viewModelScope.launch {
        authUseCase.getSignedUser().collect { user ->
            _state.update {
                it.copy(
                    greetingMessage = "${getGreetingMessage()}, ${user?.username?.split(" ")?.getOrNull(0)}"
                )
            }
        }
    }

    private fun getRecommendationActivities() = viewModelScope.launch {
        activityUseCase.getYogaActivities().collect { result ->
            when (result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
                is Result.Success -> {
                    val activities = result.data.map(ActivityItem::toExercise)
                    _state.update {
                        it.copy(
                            isLoading = false,
                            recommendationActivities = activities
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    private fun getRecommendationFeeds() = viewModelScope.launch {
        feedUseCase.getFeeds().collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            recommendationFeeds = result.data,
                        )
                    }
                }
            }
        }
    }

    private fun getRecommendationConsultations() = viewModelScope.launch {
        delay(1000.milliseconds)
        _state.update {
            it.copy(
                recommendationConsultation = dummyConsultations
            )
        }
    }

    private val dummyConsultations = listOf(
        Consultation(
            id = 0,
            drName = "Anggi kusuma M.Psi",
            price = "Rp 199.000",
            image = "https://lh3.googleusercontent.com/drive-viewer/AFGJ81rF5UGVP-FgI_zBt0YEUvtXX3sES0usJ-4G7HVZ9loFyjlJ7l8jgzuabgtHhSCJtm8vtsEKlzWssdiYxat7BUfdK2QNbA=s2560",
            desc = "Psikolog Klinis Dewasa"
        ),
        Consultation(
            id = 1,
            drName = "Sofyan Marzuki, S.Psi, M.Psi",
            price = "Rp 99.000",
            image = "https://lh3.googleusercontent.com/drive-viewer/AFGJ81qZaRMINP24jkwBBBWVxjA3RcN1A9PJPDmXLVuiXtQZ_5QTsGEFwQmupVT-QbB3wSVHExMI284NrnOjWDP4a697Q1xe=s2560",
            desc = "Psikolog Klinis Anak & Remaja"
        ),
        Consultation(
            id = 2,
            drName = "Alya Martiana M.Psi",
            price = "Rp 149.000",
            image = "https://lh3.googleusercontent.com/drive-viewer/AFGJ81rF5UGVP-FgI_zBt0YEUvtXX3sES0usJ-4G7HVZ9loFyjlJ7l8jgzuabgtHhSCJtm8vtsEKlzWssdiYxat7BUfdK2QNbA=s2560",
            desc = "Psikolog Klinis"
        ),
        Consultation(
            id = 3,
            drName = "Marzuki Ali, S.Psi, M.Psi",
            price = "Rp 99.000",
            image = "https://lh3.googleusercontent.com/drive-viewer/AFGJ81qZaRMINP24jkwBBBWVxjA3RcN1A9PJPDmXLVuiXtQZ_5QTsGEFwQmupVT-QbB3wSVHExMI284NrnOjWDP4a697Q1xe=s2560",
            desc = "Psikolog"
        ),
        Consultation(
            id = 4,
            drName = "Harry Jhumaat, S.Psi, M.Psi",
            price = "Rp 149.000",
            image = "https://lh3.googleusercontent.com/drive-viewer/AFGJ81qZaRMINP24jkwBBBWVxjA3RcN1A9PJPDmXLVuiXtQZ_5QTsGEFwQmupVT-QbB3wSVHExMI284NrnOjWDP4a697Q1xe=s2560",
            desc = "Psikolog"
        )
    )
}