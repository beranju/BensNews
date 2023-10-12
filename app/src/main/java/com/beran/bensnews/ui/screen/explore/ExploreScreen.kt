package com.beran.bensnews.ui.screen.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beran.bensnews.ui.component.ErrorView
import com.beran.bensnews.ui.screen.explore.component.ExploreAppBar
import com.beran.bensnews.ui.screen.explore.component.ExploreTabSection
import com.beran.bensnews.utils.Category
import com.beran.core.domain.model.NewsModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel,
    navigateToSearch: () -> Unit,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state

    val pagerState = rememberPagerState(
        pageCount = { Category.values().size }
    )

    LaunchedEffect(key1 = pagerState.currentPage, block = {
        val category = getCategoryByIndex(pagerState.currentPage)
        viewModel.setCategory(category)
        viewModel.getNewsByCategory()
    })
    ExploreContent(
        navigateToSearch = navigateToSearch,
        news = state.news,
        isLoading = state.isLoading,
        error = state.error,
        pagerState = pagerState,
        navigateToDetail = navigateToDetail,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExploreContent(
    navigateToSearch: () -> Unit,
    news: List<NewsModel>,
    isLoading: Boolean,
    error: String?,
    pagerState: PagerState,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        ExploreAppBar(navigateToSearch = navigateToSearch)
        Spacer(modifier = Modifier.height(24.dp))
        if (error != null) {
            ErrorView(message = error)
        } else {
            ExploreTabSection(
                news = news,
                isLoading = isLoading,
                pagerState = pagerState,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

fun getCategoryByIndex(index: Int): String {
    val values = enumValues<Category>()
    return if (index in values.indices) {
        values[index].value
    } else {
        values[0].value
    }
}

