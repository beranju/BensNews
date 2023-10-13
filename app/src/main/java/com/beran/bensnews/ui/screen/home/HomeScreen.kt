package com.beran.bensnews.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.beran.bensnews.ui.component.ErrorView
import com.beran.bensnews.ui.component.NewsItem
import com.beran.bensnews.ui.component.NewsItemShimmer
import com.beran.bensnews.ui.screen.home.component.HomeAppBar
import com.beran.bensnews.ui.screen.home.component.TopNewsSection
import com.beran.core.domain.model.NewsModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state
    val pagingNews = viewModel.getPagingNews().collectAsLazyPagingItems()
    HomeContent(
        isLoading = state.loading,
        error = state.error,
        headLineNews = state.headline,
        forYouNews = state.forYouNews,
        pagingNews = pagingNews,
        navigateToDetail = navigateToDetail,
        modifier = modifier
    )

}

@Composable
fun HomeContent(
    isLoading: Boolean,
    headLineNews: NewsModel?,
    forYouNews: List<NewsModel>,
    pagingNews: LazyPagingItems<NewsModel>,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        HomeAppBar()
        if (error != null) {
            ErrorView(message = error, modifier = Modifier.weight(1f))
        } else {
            TopNewsSection(
                isLoading = isLoading,
                data = headLineNews,
                navigateToDetail = navigateToDetail
            )
            ForYouSection(
                isLoading = isLoading,
                news = forYouNews,
                pagingNews = pagingNews,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Composable
fun ForYouSection(
    isLoading: Boolean,
    news: List<NewsModel>,
    pagingNews: LazyPagingItems<NewsModel>,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "ForYou", style = MaterialTheme.typography.labelLarge)
            Text(
                text = "View more",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.clickable { })
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(modifier = Modifier.height(400.dp)) {
            if (isLoading) {
                items(3) {
                    NewsItemShimmer()
                }
            } else {
                items(pagingNews.itemCount, key = pagingNews.itemKey { it.url }) { index ->
                    val item = pagingNews[index]
                    NewsItem(newsModel = item!!, modifier = Modifier.clickable {
                        navigateToDetail(item)
                    })
                }
                pagingNews.apply {
                    when {
                        loadState.prepend is LoadState.Loading -> {
                            items(5) {
                                NewsItemShimmer()
                            }

                        }

                        loadState.prepend is LoadState.Error -> {
                            item { ErrorView(message = "", modifier = Modifier.weight(1f)) }
                        }

                        loadState.append is LoadState.Loading -> {
                            items(5) {
                                NewsItemShimmer()
                            }

                        }

                        loadState.append is LoadState.Error -> {
                            item { ErrorView(message = "", modifier = Modifier.weight(1f)) }
                        }

                        loadState.refresh is LoadState.Loading -> {
                            items(5) {
                                NewsItemShimmer()
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            item { ErrorView(message = "", modifier = Modifier.weight(1f)) }
                        }
                    }
                }
//                items(news, key = { it.url }) { item ->
//                    NewsItem(newsModel = item, modifier = Modifier.clickable {
//                        navigateToDetail(item)
//                    })
//                }
            }
        }
    }
}