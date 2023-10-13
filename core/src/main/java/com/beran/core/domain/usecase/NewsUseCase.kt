package com.beran.core.domain.usecase

import androidx.paging.PagingData
import com.beran.core.domain.common.Resource
import com.beran.core.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getPagingNews(): Flow<PagingData<NewsModel>>
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

    fun getAllSavedNews(): Flow<Resource<List<NewsModel>>>
    suspend fun isSaved(url: String): Boolean
    suspend fun insertNewsToDb(newsModel: NewsModel)
    suspend fun deleteNewsFromDb(newsModel: NewsModel)
}