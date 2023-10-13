package com.beran.bensnews.ui.screen.explore.search

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beran.bensnews.R
import com.beran.bensnews.ui.component.ErrorView
import com.beran.bensnews.ui.component.NewsItem
import com.beran.bensnews.ui.component.NewsItemShimmer
import com.beran.bensnews.ui.component.Spinner
import com.beran.bensnews.ui.screen.explore.component.SearchAppBar
import com.beran.core.domain.model.NewsModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state

    SearchContent(
        searchText = state.query,
        onSearchChange = viewModel::onSearchTextChange,
        onSearchQuery = viewModel::getNewsByQuery,
        selectedSort = state.sort,
        onSelectedSort = viewModel::onSelectedSortChange,
        news = state.news,
        isLoading = state.isLoading,
        error = state.error,
        navigateToDetail = navigateToDetail,
        modifier = modifier
    )
}

@Composable
fun SearchContent(
    searchText: String,
    onSearchChange: (String) -> Unit,
    selectedSort: String,
    onSelectedSort: (String) -> Unit,
    onSearchQuery: () -> Unit,
    news: List<NewsModel>,
    isLoading: Boolean,
    error: String?,
    navigateToDetail: (NewsModel) -> Unit,
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.sort_by))
            Spinner(selected = selectedSort, onChangeSelected = onSelectedSort)
        }
        Spacer(modifier = Modifier.height(16.dp))
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
                        NewsItem(
                            newsModel = item,
                            modifier = Modifier.clickable { navigateToDetail(item) })
                    }
                }
            }
        }
    }
}
