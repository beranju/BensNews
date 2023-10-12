package com.beran.core.domain.repository

import com.beran.core.domain.common.Resource
import com.beran.core.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getAllNews(
        page: Int? = null,
        pageSize: Int? = null,
        country: String? = null,
        category: String? = null
    ): Flow<Resource<List<NewsModel>>>

    fun getNewsByQuery(
        page: Int? = null,
        pageSize: Int? = null,
        query: String,
        searchIn: String? = null
    ): Flow<Resource<List<NewsModel>>>
}