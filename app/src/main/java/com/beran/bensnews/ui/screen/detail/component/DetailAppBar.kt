package com.beran.bensnews.ui.screen.detail.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.IosShare
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beran.bensnews.ui.theme.BensNewsTheme

@Composable
fun DetailAppBar(
    isSaved: Boolean,
    navigateBack: () -> Unit,
    onSave: () -> Unit,
    onShare: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 24.dp)
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack, contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .clickable { navigateBack() }
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Rounded.IosShare, contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .clickable { onShare() }
        )
        Spacer(modifier = Modifier.width(24.dp))
        Icon(
            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = null,
            tint = if (isSaved) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(26.dp)
                .clickable {
                    onSave()
                }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailAppBarPrev() {
    BensNewsTheme {
        DetailAppBar(isSaved = true, navigateBack = {}, onShare = {}, onSave = {})
    }
}