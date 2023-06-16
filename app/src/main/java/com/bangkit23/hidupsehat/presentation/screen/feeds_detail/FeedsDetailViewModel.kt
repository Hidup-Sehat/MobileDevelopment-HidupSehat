package com.bangkit23.hidupsehat.presentation.screen.feeds_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit23.hidupsehat.domain.usecase.feed.FeedUseCase
import com.bangkit23.hidupsehat.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedsDetailViewModel @Inject constructor(
    private val feedUseCase: FeedUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(FeedsDetailState())
    val state: StateFlow<FeedsDetailState>
        get() = _state.asStateFlow()

    fun onEvent(event : FeedDetailEvent){
        when(event){
            is FeedDetailEvent.OnGetDetailFeed -> {
                detailFeed(event.id)
            }
        }
    }



    private fun detailFeed(id : String){
        viewModelScope.launch {
            feedUseCase.feedDetailById(id)
                .collect{ result ->
                when(result){
                    is Result.Loading -> {}
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                title = result.data.title,
                                link = result.data.link
                            )
                        }
                    }
                    is Result.Error -> {}
                }
            }
        }
    }

}