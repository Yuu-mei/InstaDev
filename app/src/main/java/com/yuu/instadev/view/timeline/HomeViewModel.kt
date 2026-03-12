package com.yuu.instadev.view.timeline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import com.yuu.instadev.domain.usecase.TimelineGetLikedReelsUseCase
import com.yuu.instadev.domain.usecase.TimelineHomeUseCase
import com.yuu.instadev.domain.usecase.TimelineLikeReelUseCase
import com.yuu.instadev.domain.usecase.TimelineObserveReelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: TimelineHomeUseCase,
    private val timelineLikeReelUseCase: TimelineLikeReelUseCase,
    private val timelineGetLikedReelsUseCase: TimelineGetLikedReelsUseCase,
    private val timelineObserveReelsUseCase: TimelineObserveReelsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        loadInitialReels()
        observeReels()
    }

    fun likeReel(reelID: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                val isReelLiked = timelineLikeReelUseCase.invoke(reelID)

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        likedReels = state.likedReels + (reelID to isReelLiked)
                    )
                }
                Log.i("REEL", "REEL LIKED")
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
                Log.e("REEL", "Error liking reel \n ${e.message}")
            }
        }
    }

    private fun loadInitialReels() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    isLoading = true,
                    error = null,
                    reels = emptyList()
                )
            }

            try {
                val reels = homeUseCase.invoke()

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = null,
                        reels = reels
                    )
                }

                loadLikedReels()

                Log.i("REEL", "Success")
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = e.message,
                        reels = emptyList()
                    )
                }
                Log.i("REEL", "Error loading initial reels \n ${e.message}")
            }
        }
    }

    private fun loadLikedReels() {
        viewModelScope.launch {
            try {
                val likedReels = timelineGetLikedReelsUseCase.invoke()

                _uiState.update { state ->
                    state.copy(
                        likedReels = likedReels
                    )
                }
            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        error = e.message,
                        likedReels = emptyMap()
                    )
                }
                Log.i("REEL", "Error retrieving liked reels \n ${e.message}")
            }
        }
    }

    private fun observeReels() {
        viewModelScope.launch {
            timelineObserveReelsUseCase.invoke()
                .collect { reels ->
                    _uiState.update { state ->
                        state.copy(
                            reels = reels
                        )
                    }
                }
        }
    }
}

data class HomeUIState(
    val reels: List<ReelsFirebaseEntity> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val likedReels: Map<String, Boolean> = emptyMap<String, Boolean>()
)