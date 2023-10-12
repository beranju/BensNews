package com.beran.bensnews.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beran.bensnews.ui.screen.home.component.HomeAppBar
import com.beran.bensnews.ui.screen.home.component.TopNewsSection
import com.beran.bensnews.ui.theme.BensNewsTheme
import com.beran.core.domain.model.NewsModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    HomeContent(modifier)

}

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        HomeAppBar()
        TopNewsSection(NewsModel(""))
        ForYouSection()
    }
}

@Composable
fun ForYouSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)) {
        Row {
            Text(text = "ForYou", style = MaterialTheme.typography.labelLarge)
            Text(
                text = "View more",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.clickable { })
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(modifier = Modifier.height(400.dp)){
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeContentPrev() {
    BensNewsTheme {
        HomeContent()
    }
}