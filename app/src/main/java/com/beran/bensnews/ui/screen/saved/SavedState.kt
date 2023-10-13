package com.beran.bensnews.ui.screen.saved

import com.beran.core.domain.model.NewsModel

data class SavedState(
    val isLoading: Boolean = false,
    val news: List<NewsModel> = emptyList(),
    val error: String? = null
)
