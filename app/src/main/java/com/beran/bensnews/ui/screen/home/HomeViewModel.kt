package com.beran.bensnews.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.beran.core.domain.common.Resource
import com.beran.core.domain.usecase.NewsUseCase
import com.beran.core.utils.Constants
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class HomeViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        fetchAllNews()
    }

    fun getPagingNews() = newsUseCase.getPagingNews().cachedIn(viewModelScope)

    private fun fetchAllNews(isRefresh: Boolean = false) {
        viewModelScope.launch {
            newsUseCase.getAllNews(pageSize = 10).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        // ** memfilter data dari item yang terhapus oleh provider api nya
                        val data = result.data.filter { it.url != Constants.REMOVED_URL }
                        val headlineNews = data.random()
                        state = state.copy(
                            loading = false,
                            error = null,
                            headline = headlineNews,
                            isRefresh = false
                        )
                    }

                    is Resource.Error -> state = state.copy(
                        loading = false,
                        error = result.message,
                        headline = null,
                        isRefresh = false
                    )

                    is Resource.Loading -> state = state.copy(
                        loading = true,
                        error = null,
                        headline = null,
                        isRefresh = isRefresh
                    )
                }
            }
        }
    }

    fun refresh() {
        fetchAllNews(isRefresh = true)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}