package com.beran.bensnews.ui.screen.explore.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beran.core.domain.common.Resource
import com.beran.core.domain.usecase.NewsUseCase
import com.beran.core.utils.Constants.REMOVED_URL
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SearchViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set


    fun onSearchTextChange(text: String) {
        state = state.copy(query = text)
    }

    fun getNewsByQuery() {
        viewModelScope.launch {
            newsUseCase.getNewsByQuery(query = state.query).collect { result ->
                state = when (result) {
                    is Resource.Loading -> state.copy(isLoading = true, error = null, news = emptyList())

                    is Resource.Error -> state.copy(isLoading = false, error = result.message, news = emptyList())

                    is Resource.Success -> {
                        val data = result.data.filter { it.url != REMOVED_URL }
                        state.copy(isLoading = false, error = null, news = data)
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}