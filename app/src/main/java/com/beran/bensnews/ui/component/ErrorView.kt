package com.beran.bensnews.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.beran.bensnews.R
import com.beran.bensnews.ui.theme.BensNewsTheme

@Composable
fun ErrorView(
    message: String,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))
    val progress by animateLottieCompositionAsState(composition = composition)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(150.dp)) {
            LottieAnimation(composition = composition, progress = { progress })
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = message, style = MaterialTheme.typography.labelMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorViewPrev() {
    BensNewsTheme {
        ErrorView(message = "Some error view will shown here")
    }
}