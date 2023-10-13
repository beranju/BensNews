package com.beran.bensnews.ui.screen.saved

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beran.core.domain.common.Resource
import com.beran.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SavedViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    var state by mutableStateOf(SavedState())
        private set

    init {
        getAllSavedNews()
    }

    private fun getAllSavedNews() {
        viewModelScope.launch {
            newsUseCase.getAllSavedNews().collect { result ->
                state = when (result) {
                    is Resource.Loading -> state.copy(
                        isLoading = true,
                        error = null,
                        news = emptyList()
                    )

                    is Resource.Error -> state.copy(
                        isLoading = false,
                        error = result.message,
                        news = emptyList()
                    )

                    is Resource.Success -> state.copy(
                        isLoading = false,
                        error = null,
                        news = result.data
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}