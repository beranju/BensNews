package com.beran.core.domain.usecase

import androidx.paging.PagingData
import com.beran.core.domain.common.Resource
import com.beran.core.domain.model.NewsModel
import com.beran.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val repository: INewsRepository) : NewsUseCase {
    override fun getPagingNews(): Flow<PagingData<NewsModel>> = repository.getPagingNews()
    override fun getAllNews(
        page: Int?,
        pageSize: Int?,
        country: String?,
        category: String?
    ): Flow<Resource<List<NewsModel>>> =
        repository.getAllNews(category = category, country = country)

    override fun getNewsByQuery(
        page: Int?,
        pageSize: Int?,
        query: String,
        sortBy: String?
    ): Flow<Resource<List<NewsModel>>> =
        repository.getNewsByQuery(query = query, sortBy = sortBy)

    override fun getAllSavedNews(): Flow<Resource<List<NewsModel>>> = repository.getAllSavedNews()

    override suspend fun isSaved(url: String): Boolean = repository.isSaved(url)

    override suspend fun insertNewsToDb(newsModel: NewsModel) = repository.insertNewsToDb(newsModel)

    override suspend fun deleteNewsFromDb(newsModel: NewsModel) =
        repository.deleteNewsFromDb(newsModel)
}