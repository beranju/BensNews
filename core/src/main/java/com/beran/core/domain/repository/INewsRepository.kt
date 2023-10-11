package com.beran.core.domain.repository

import com.beran.core.domain.common.Resource
import com.beran.core.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getAllNews(): Flow<Resource<List<NewsModel>>>
}