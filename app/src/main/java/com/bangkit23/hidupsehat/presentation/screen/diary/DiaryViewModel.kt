package com.bangkit23.hidupsehat.presentation.screen.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.diary.DiaryUseCase
import com.bangkit23.hidupsehat.domain.usecase.user.UserUseCase
import com.bangkit23.hidupsehat.util.DateConverter
import com.bangkit23.hidupsehat.util.DateHelper
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryUseCase: DiaryUseCase,
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DiaryState())
    val state = _state.asStateFlow()

    private val currentDate = DateConverter.getCurrentDateFormatted()

    fun onEvent(event: DiaryEvent){
        when(event){
            is DiaryEvent.OnSaveDiary -> {
                _state.update {
                    it.copy(
                        emotionPositive = event.positive,
                        emotionNegative = event.negative,
                        source = event.source,
                        note = event.note
                    )
                }
                addDiary(
                    positive = _state.value.emotionPositive ?: "",
                    negative = _state.value.emotionNegative ?: "",
                    source = _state.value.source ?: "",
                    note = _state.value.note ?: "")
            }
            is DiaryEvent.OnNoteChanged -> {
                _state.update {
                    it.copy(
                        note = event.note
                    )
                }
            }
        }
    }


    private fun addDiary(positive : String, negative : String, source : String, note : String) = viewModelScope.launch {
        diaryUseCase.addDiary(
            date = currentDate,
            lastUpdated = DateHelper.getCurrentDate(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
            note = note,
            emotionSource = source,
            emotionPositive = positive,
            emotionNegative = negative
        ).collect { result ->
            when (result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> addPoints()
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            diaryError = result.message
                        )
                    }
                }
            }
        }
    }

    private fun addPoints() = viewModelScope.launch {
        userUseCase.addUserPoints(points = 50).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isDiaryDone = true
                        )
                    }
                }
            }
        }
    }
}