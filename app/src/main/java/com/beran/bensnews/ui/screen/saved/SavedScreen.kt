package com.beran.bensnews.ui.screen.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beran.bensnews.R
import com.beran.bensnews.ui.component.EmptyView
import com.beran.bensnews.ui.component.ErrorView
import com.beran.bensnews.ui.component.NewsItem
import com.beran.bensnews.ui.component.NewsItemShimmer
import com.beran.core.domain.model.NewsModel

@Composable
fun SavedScreen(
    viewModel: SavedViewModel,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state
    SavedContent(
        news = state.news,
        isLoading = state.isLoading,
        error = state.error,
        navigateToDetail = navigateToDetail,
        modifier = modifier
    )
}

@Composable
fun SavedContent(
    news: List<NewsModel>,
    isLoading: Boolean,
    error: String?,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        SavedAppBar()
        Spacer(modifier = Modifier.height(24.dp))
        SavedCardHint()
        Spacer(modifier = Modifier.height(24.dp))
        if (error != null) {
            ErrorView(message = error, modifier = Modifier.weight(1f))
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                if (isLoading) {
                    items(5) {
                        NewsItemShimmer()
                    }
                } else {
                    if (news.isEmpty()) {
                        item {
                            EmptyView(
                                message = "Your saved news is empty",
                                modifier = Modifier.weight(1f)
                            )
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
}

@Composable
fun SavedCardHint(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.img_koran),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(
                text = stringResource(R.string.explore_hint),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(24.dp)
            )

        }
    }
}

@Composable
fun SavedAppBar(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = stringResource(R.string.saved_news_title),
            style = MaterialTheme.typography.labelLarge
        )
    }
}
