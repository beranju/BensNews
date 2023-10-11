package com.beran.core.domain.usecase

import com.beran.core.domain.common.Resource
import com.beran.core.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(
        page: Int? = null,
        pageSize: Int? = null,
        country: String? = null,
        category: String? = null
    ): Flow<Resource<List<NewsModel>>>
}