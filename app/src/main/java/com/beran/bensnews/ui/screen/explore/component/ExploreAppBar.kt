package com.beran.bensnews.ui.screen.explore.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beran.bensnews.R
import com.beran.bensnews.ui.theme.BensNewsTheme

@Composable
fun ExploreAppBar(
    navigateToSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
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
}

@Preview(showBackground = true)
@Composable
private fun ExploreAppBarPrev() {
    BensNewsTheme {
        ExploreAppBar(navigateToSearch = {})
    }
}