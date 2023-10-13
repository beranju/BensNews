package com.beran.bensnews.ui.screen.explore.search

import com.beran.bensnews.utils.Sort
import com.beran.core.domain.model.NewsModel

data class SearchState(
    val query: String = "",
    val sort: String = Sort.PublishedAt.value,
    val isLoading: Boolean = false,
    val news: List<NewsModel> = emptyList(),
    val error: String? = null
)
