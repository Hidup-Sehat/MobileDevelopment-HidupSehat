package com.bangkit23.hidupsehat.presentation.screen.emotion_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.diary.DiaryUseCase
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmotionHistoryViewModel @Inject constructor(
    private val useCase: DiaryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(EmotionHistoryState())
    val state: StateFlow<EmotionHistoryState>
        get() = _state.asStateFlow()

    init {
        onEvent(EmotionHistoryEvent.OnGetDiaryByDate("2023-06-14"))
    }

    private fun onEvent(event : EmotionHistoryEvent){
        when(event){
            is EmotionHistoryEvent.OnGetDiaryByDate -> {
                viewModelScope.launch {
                    useCase.getDiaryByDate(event.date)
                        .collect{result ->
                            when(result){
                                is Result.Error -> {

                                }
                                is Result.Loading -> {}
                                is Result.Success -> {
                                    _state.update {
                                        it.copy(
                                            data = result.data
                                        )
                                    }
                                }
                            }

                        }
                }
            }
        }
    }

}