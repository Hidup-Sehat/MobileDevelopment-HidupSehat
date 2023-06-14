package com.bangkit23.hidupsehat.presentation.screen.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.feed.FeedUseCase
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(
    private val feedUseCase: FeedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FeedState())
    val state = _state.asStateFlow()

    init {
        onEvent(FeedEvent.OnGetFeeds(listOf()))
    }

    private fun onEvent(event : FeedEvent){
        when(event){
            is FeedEvent.OnGetFeeds ->{
                viewModelScope.launch {
                    feedUseCase.getFeeds()
                        .collect {result ->
                            when (result) {
                                is Result.Error -> {

                                }
                                is Result.Loading -> {

                                }
                                is Result.Success -> {
                                    _state.update {
                                        it.copy(
                                            feedResult = result.data
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