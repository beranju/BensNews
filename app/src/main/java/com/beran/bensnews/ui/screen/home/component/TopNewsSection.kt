package com.beran.bensnews.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beran.bensnews.R
import com.beran.bensnews.ui.theme.BensNewsTheme
import com.beran.core.domain.model.NewsModel
import com.beran.core.utils.DateUtils

@Composable
fun TopNewsSection(
    isLoading: Boolean,
    navigateToDetail: (NewsModel) -> Unit,
    data: NewsModel?
) {
    if (isLoading) {
        TopNewsShimmer()
    } else {
        TopNewsContent(data = data, navigateToDetail = navigateToDetail)
    }
}

@Composable
fun TopNewsContent(
    data: NewsModel?,
    navigateToDetail: (NewsModel) -> Unit,
    modifier: Modifier = Modifier
) {

    val timeAgo = DateUtils.getTimeAgo(data?.publishedAt.orEmpty())

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clickable { navigateToDetail(data!!) }
    ) {
        Box {

            AsyncImage(
                model = data?.urlToImage,
                placeholder = painterResource(id = R.drawable.img_koran),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Trending",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = data?.title.orEmpty(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = data?.author ?: data?.source.orEmpty(),
                style = MaterialTheme.typography.bodySmall
            )
            Text(text = timeAgo, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNewsSectionPrev() {
    BensNewsTheme {
        TopNewsContent(
            data = NewsModel(
                url = "",
                publishedAt = "1 hour ago",
                urlToImage = null,
                title = "Example of news title should like this style, this title will be remove next tine",
                author = "beranju",
            ),
            navigateToDetail = {}
        )
    }
}