package com.beran.bensnews.ui.screen.explore

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beran.bensnews.R
import com.beran.bensnews.ui.component.ErrorView
import com.beran.bensnews.ui.screen.explore.component.ExploreTabSection
import com.beran.bensnews.utils.Category
import com.beran.core.domain.model.NewsModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel,
    navigateToSearch: () -> Unit,
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

    var searchText by remember {
        mutableStateOf("")
    }
    ExploreContent(
        searchText = searchText,
        onChangeSearchText = { searchText = it },
        navigateToSearch = navigateToSearch,
        news = state.news,
        isLoading = state.isLoading,
        error = state.error,
        pagerState = pagerState,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExploreContent(
    searchText: String,
    onChangeSearchText: (String) -> Unit,
    navigateToSearch: () -> Unit,
    news: List<NewsModel>,
    isLoading: Boolean,
    error: String?,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(MaterialTheme.colorScheme.surface)
                .border(
                    1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(30.dp)
                )
                .clip(RoundedCornerShape(30.dp))
                .padding(vertical = 10.dp, horizontal = 12.dp)
                .clickable { navigateToSearch() }
        ) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = stringResource(R.string.search_hint))
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (error != null) {
            ErrorView(message = error)
        } else {
            ExploreTabSection(
                news = news,
                isLoading = isLoading,
                pagerState = pagerState
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


//@Preview(showBackground = true)
//@Composable
//private fun ExploreContentPrev() {
//    BensNewsTheme {
////        ExploreContent(searchText = "", onChangeSearchText = {})
//    }
//}

