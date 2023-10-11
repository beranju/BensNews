package com.beran.core.data.remote.retrofit

import com.beran.core.data.remote.response.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun fetchAllNews(
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? = null,
        @Query("country") country: String? = null,
        @Query("category") category: String? = null
    ): Response<NewsApiResponse>

    @GET("everything")
    suspend fun findNewsByQuery(
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? = null,
        @Query("q") query: String? = null,
    ): Response<NewsApiResponse>
}