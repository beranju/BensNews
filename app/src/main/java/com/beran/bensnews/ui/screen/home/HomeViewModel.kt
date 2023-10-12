package com.beran.bensnews.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beran.core.domain.common.Resource
import com.beran.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        fetchAllNews()
    }

    private fun fetchAllNews() {
        viewModelScope.launch {
            newsUseCase.getAllNews().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val headlineNews = result.data.random()
                        state = state.copy(
                            loading = false,
                            error = null,
                            headline = headlineNews,
                            forYouNews = result.data
                        )
                    }

                    is Resource.Error -> state = state.copy(
                        loading = false,
                        error = result.message,
                        headline = null,
                        forYouNews = emptyList()
                    )

                    is Resource.Loading -> state = state.copy(
                        loading = true,
                        error = null,
                        headline = null,
                        forYouNews = emptyList()
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