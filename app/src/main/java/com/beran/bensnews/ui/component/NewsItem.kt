package com.beran.bensnews.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun NewsItem(newsModel: NewsModel, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Column(
            modifier = modifier
                .height(100.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = newsModel.title.orEmpty(),
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = newsModel.author.orEmpty(), style = MaterialTheme.typography.bodySmall)
                Text(text = newsModel.publishedAt, style = MaterialTheme.typography.bodySmall)
            }
        }
        Spacer(modifier = Modifier.width(24.dp))
        if (newsModel.urlToImage == null) {
            Image(
                painter = painterResource(id = R.drawable.img_koran), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        } else {
            AsyncImage(
                model = newsModel.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NewsItemPrev() {
    BensNewsTheme {
        NewsItem(
            newsModel = NewsModel(
                publishedAt = "1 hour ago",
                urlToImage = null,
                title = "Example of news title should like this style, this title will be remove next tine",
                author = "beranju",
            )
        )
    }
}