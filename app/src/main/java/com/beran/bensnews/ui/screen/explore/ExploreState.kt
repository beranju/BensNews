package com.beran.bensnews.ui.screen.explore

import com.beran.bensnews.utils.Category
import com.beran.core.domain.model.NewsModel

data class ExploreState(
    val isLoading: Boolean = false,
    val news: List<NewsModel> = emptyList(),
    val category: String = Category.General.value,
    val error: String? = null
)
