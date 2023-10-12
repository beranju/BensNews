package com.beran.bensnews.ui.component

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beran.bensnews.ui.theme.BensNewsTheme

@Composable
fun NewsItemShimmer(modifier: Modifier = Modifier) {

    val brush = createShimmerEffect()

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
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(
                    modifier = Modifier
                        .width(80.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(brush)
                )
                Spacer(
                    modifier = Modifier
                        .width(50.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(brush)
                )
            }
        }
        Spacer(modifier = Modifier.width(24.dp))
        Spacer(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsItemShimmerPrev() {
    BensNewsTheme {
        NewsItemShimmer()
    }
}
