package com.yuu.instadev.view.timeline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuu.instadev.domain.entity.ReelsFirebaseEntity
import com.yuu.instadev.domain.usecase.TimelineHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: TimelineHomeUseCase): ViewModel(){
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        loadInitialReels()
    }

    private fun loadInitialReels(){
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true, error = null, reels = emptyList())
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

                Log.i("REEL", "Success")
                Log.i("REEL", reels[0].text)
            }catch (e: Exception){
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        error = e.message,
                        reels = emptyList()
                    )
                }
                Log.i("REEL", "Error ${e.message}")
            }
        }
    }
}

data class HomeUIState(
    val reels: List<ReelsFirebaseEntity> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)