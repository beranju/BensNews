package com.beran.core.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.beran.core.data.remote.response.ArticlesItem
import com.beran.core.data.remote.retrofit.ApiService
import com.beran.core.domain.model.NewsModel
import com.beran.core.utils.Constants.INITIAL_PAGE_INDEX
import com.beran.core.utils.DataMapper

class NewsPagingSource(private val apiService: ApiService) : PagingSource<Int, NewsModel>() {
    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val result = apiService.fetchAllNews(page = position, pageSize = params.loadSize)
            val articlesItem: List<ArticlesItem> =
                result.body()?.articles?.filterNotNull() ?: emptyList()
            val data = articlesItem.map { DataMapper.articleItemToNewsModel(it) }

            LoadResult.Page(
                data = data,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}