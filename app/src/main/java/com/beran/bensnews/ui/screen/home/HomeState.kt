package com.beran.bensnews.ui.screen.home

import com.beran.core.domain.model.NewsModel

data class HomeState(
    val loading: Boolean = false,
    val headline: NewsModel? = null,
    val forYouNews: List<NewsModel> = emptyList(),
    val error: String? = null
)
