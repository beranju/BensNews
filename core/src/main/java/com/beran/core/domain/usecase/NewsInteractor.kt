package com.beran.core.domain.usecase

import com.beran.core.data.repository.NewsRepository
import com.beran.core.domain.common.Resource
import com.beran.core.domain.model.NewsModel
import com.beran.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val repository: INewsRepository): NewsUseCase {
    override fun getAllNews(
        page: Int?,
        pageSize: Int?,
        country: String?,
        category: String?
    ): Flow<Resource<List<NewsModel>>>  = repository.getAllNews()
}