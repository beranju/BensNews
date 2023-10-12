package com.beran.bensnews.ui.screen.explore.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.beran.bensnews.ui.component.NewsItem
import com.beran.bensnews.ui.component.NewsItemShimmer
import com.beran.bensnews.utils.Category
import com.beran.core.domain.model.NewsModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreTabSection(
    news: List<NewsModel>,
    pagerState: PagerState,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Tabs(pagerState = pagerState)
        TabContent(news = news, pagerState = pagerState, isLoading = isLoading)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabContent(
    news: List<NewsModel>,
    isLoading: Boolean,
    pagerState: PagerState
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> TabContentPage(news = news, isLoading = isLoading)
            1 -> TabContentPage(news = news, isLoading = isLoading)
            2 -> TabContentPage(news = news, isLoading = isLoading)
            3 -> TabContentPage(news = news, isLoading = isLoading)
            4 -> TabContentPage(news = news, isLoading = isLoading)
            5 -> TabContentPage(news = news, isLoading = isLoading)
            6 -> TabContentPage(news = news, isLoading = isLoading)
        }
    }
}

@Composable
fun TabContentPage(
    news: List<NewsModel>,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        Category.values().forEachIndexed { index, item ->
            Box(modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (pagerState.currentPage == index) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface)
                        .padding(vertical = 6.dp, horizontal = 10.dp)
                        .clickable {
                            scope.launch { pagerState.animateScrollToPage(index) }
                        }
                )
            }
        }
    }
}
