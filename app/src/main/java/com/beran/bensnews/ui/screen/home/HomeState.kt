package com.beran.bensnews.ui.screen.home

import androidx.paging.PagingData
import com.beran.core.domain.model.NewsModel

data class HomeState(
    val loading: Boolean = false,
    val headline: NewsModel? = null,
    val pagingNews: PagingData<NewsModel> = PagingData.empty(),
    val error: String? = null
)
