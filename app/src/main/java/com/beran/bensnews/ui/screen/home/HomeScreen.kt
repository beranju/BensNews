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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beran.bensnews.ui.component.ErrorView
import com.beran.bensnews.ui.component.NewsItem
import com.beran.bensnews.ui.component.NewsItemShimmer
import com.beran.bensnews.ui.screen.home.component.HomeAppBar
import com.beran.bensnews.ui.screen.home.component.TopNewsSection
import com.beran.core.domain.model.NewsModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state
    HomeContent(
        isLoading = state.loading,
        error = state.error,
        headLineNews = state.headline,
        forYouNews = state.forYouNews,
        modifier = modifier
    )

}

@Composable
fun HomeContent(
    isLoading: Boolean,
    headLineNews: NewsModel?,
    forYouNews: List<NewsModel>,
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
            ErrorView(message = error)
        } else {
            TopNewsSection(isLoading = isLoading, data = headLineNews)
            ForYouSection(isLoading = isLoading, news = forYouNews)
        }
    }
}

@Composable
fun ForYouSection(
    isLoading: Boolean,
    news: List<NewsModel>,
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
                items(news, key = { it.url }) { item ->
                    NewsItem(newsModel = item)
                }
            }
        }
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun HomeContentPrev() {
//    BensNewsTheme {
//        HomeContent()
//    }
//}