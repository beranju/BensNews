package com.beran.core.data.repository

import com.beran.core.data.remote.response.NewsApiResponse
import com.beran.core.data.remote.retrofit.ApiService
import com.beran.core.data.utils.DataDummy
import com.beran.core.domain.common.Resource
import com.beran.core.domain.model.NewsModel
import com.beran.core.domain.repository.INewsRepository
import com.beran.core.utils.DataMapper
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

class NewsRepositoryTest {
    private lateinit var newsRepository: INewsRepository

    @Mock
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        newsRepository = NewsRepository(apiService)
    }

    @Test
    fun `getNews successful response`(): Unit = runBlocking {
        val expected =
            Response.success(NewsApiResponse(articles = DataDummy.generateListOfArticleItems()))
        `when`(apiService.fetchAllNews()).thenReturn(expected)
        var result: Resource<List<NewsModel>>? = null
        newsRepository.getAllNews().collect { resource ->
            result = resource
        }
        assertTrue(result is Resource.Success)
    }
}