package com.beran.bensnews.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beran.core.domain.model.NewsModel
import com.beran.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    private var _isSaved: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> get() = _isSaved

    fun isNewsSaved(url: String) {
        viewModelScope.launch {
            _isSaved.value = newsUseCase.isSaved(url)
        }
    }

    fun setSaveNews(newsModel: NewsModel) {
        viewModelScope.launch {
            if (isSaved.value) {
                newsUseCase.deleteNewsFromDb(newsModel)
            } else {
                newsUseCase.insertNewsToDb(newsModel)
            }
            isNewsSaved(newsModel.url)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}