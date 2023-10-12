package com.beran.bensnews.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beran.bensnews.ui.component.createShimmerEffect
import com.beran.bensnews.ui.theme.BensNewsTheme

@Composable
fun TopNewsShimmer(modifier: Modifier = Modifier) {
    val brush = createShimmerEffect()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Box {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(brush)
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(12.dp)) {
                Spacer(
                    modifier = Modifier
                        .width(80.dp)
                        .height(24.dp)
                        .align(Alignment.TopStart)
                        .clip(RoundedCornerShape(4.dp))
                        .background(brush)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(brush)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Spacer(
                modifier = Modifier
                    .width(80.dp)
                    .height(18.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
            Spacer(
                modifier = Modifier
                    .width(80.dp)
                    .height(18.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopNewsShimmerPrev() {
    BensNewsTheme {
        TopNewsShimmer()
    }
}