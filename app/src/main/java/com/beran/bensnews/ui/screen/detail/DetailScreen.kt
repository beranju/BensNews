package com.beran.bensnews.ui.screen.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.beran.bensnews.R
import com.beran.bensnews.ui.screen.detail.component.DetailAppBar
import com.beran.bensnews.ui.theme.BensNewsTheme
import com.beran.core.domain.model.NewsModel
import com.beran.core.utils.DateUtils

@Composable
fun DetailScreen(
    data: NewsModel,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        DetailAppBar(
            navigateBack = navigateBack,
            onShare = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, data.title)
                    putExtra(Intent.EXTRA_TEXT, "${data.title}. Read more here ${data.url}")
                }
                context.startActivity(Intent.createChooser(intent, "Bagikan berita mu"))
            },
            onSave = {

            }
        )
        DetailContent(data = data, modifier = Modifier.weight(1f))
        Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
            Button(
                onClick = {
                    val url = Uri.parse(data.url)
                    val intent = Intent(Intent.ACTION_VIEW, url)
                    context.startActivity(Intent.createChooser(intent, "Buka Browser"))
                }, modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Baca Selengkapnya")
            }
        }
    }

}

@Composable
fun DetailContent(
    data: NewsModel,
    modifier: Modifier = Modifier
) {
    val scrollContent = rememberScrollState()
    val timeAgo = DateUtils.getTimeAgo(data.publishedAt.orEmpty())
    Column(
        modifier = modifier
            .padding(vertical = 26.dp, horizontal = 24.dp)
            .verticalScroll(scrollContent)
    ) {
        Text(text = data.title.orEmpty(), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = data.author ?: data.source.orEmpty(),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelSmall
            )
            Text(text = timeAgo, style = MaterialTheme.typography.labelSmall)
        }
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            model = data.urlToImage, contentDescription = null,
            placeholder = painterResource(id = R.drawable.img_koran),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = data.content.orEmpty(),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailContentPrev() {
    BensNewsTheme {
        DetailContent(
            data = NewsModel(
                url = "",
                publishedAt = "1 hour ago",
                urlToImage = null,
                title = "Example of news title should like this style, this title will be remove next tine",
                author = "beranju",
            )
        )
    }
}
