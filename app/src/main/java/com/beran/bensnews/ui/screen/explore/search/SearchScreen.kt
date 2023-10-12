package com.beran.bensnews.ui.screen.explore.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beran.bensnews.ui.component.ErrorView
import com.beran.bensnews.ui.component.NewsItem
import com.beran.bensnews.ui.component.NewsItemShimmer
import com.beran.bensnews.ui.screen.explore.component.SearchAppBar
import com.beran.core.domain.model.NewsModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state

    SearchContent(
        searchText = state.query,
        onSearchChange = viewModel::onSearchTextChange,
        onSearchQuery = viewModel::getNewsByQuery,
        news = state.news,
        isLoading = state.isLoading,
        error = state.error,
        modifier = modifier
    )
}

@Composable
fun SearchContent(
    searchText: String,
    onSearchChange: (String) -> Unit,
    onSearchQuery: () -> Unit,
    news: List<NewsModel>,
    isLoading: Boolean,
    error: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        SearchAppBar(
            searchText = searchText,
            onChangeSearchText = onSearchChange,
            onSearchQuery = onSearchQuery
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Result for $searchText")
        if (error != null) {
            ErrorView(message = error)
        } else {
            LazyColumn {
                if (isLoading) {
                    items(5) {
                        NewsItemShimmer()
                    }
                } else {
                    items(news, key = { it.url }) { item ->
                        NewsItem(newsModel = item)
                    }
                }
            }
        }
    }
}
