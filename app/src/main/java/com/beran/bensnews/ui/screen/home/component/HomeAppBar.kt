package com.beran.bensnews.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beran.bensnews.R
import com.beran.bensnews.ui.theme.BensNewsTheme

@Composable
fun HomeAppBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Headline",
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 24.sp)
            )
            Text(text = "Today, October 23th")
        }
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = stringResource(R.string.notification_icon)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAppBarPrev() {
    BensNewsTheme {
        HomeAppBar()
    }
}