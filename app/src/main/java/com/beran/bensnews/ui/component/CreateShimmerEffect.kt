package com.beran.bensnews.ui.component

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

@Composable
fun createShimmerEffect(): Brush {
    val gradient = listOf(
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
    )
    val transition = rememberInfiniteTransition()

    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            )
        )
    )

    return Brush.linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(translateAnimation.value, translateAnimation.value)
    )
}