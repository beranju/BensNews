package com.beran.bensnews.ui.screen.explore

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

class ExploreViewModel(private val newsUseCase: NewsUseCase): ViewModel() {
    var state by mutableStateOf(ExploreState())
        private set

    init {
        getNewsByCategory()
    }

    fun getNewsByCategory(){
        viewModelScope.launch {
            newsUseCase.getAllNews(category = state.category)
                .collect{result ->
                    state = when(result){
                        is Resource.Loading -> {
                            state.copy(isLoading = true, error = null, news = emptyList())
                        }

                        is Resource.Success -> {
                            // ** memfilter data dari item yang terhapus oleh provider api nya
                            val data = result.data.filter { it.url != REMOVED_URL }
                            state.copy(isLoading = false, error = null, news = data)
                        }

                        is Resource.Error -> {
                            state.copy(isLoading = false, error = result.message, news = emptyList())
                        }
                    }
                }
        }
    }

    fun setCategory(category: String){
        state = state.copy(category = category)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}