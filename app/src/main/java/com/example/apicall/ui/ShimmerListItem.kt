package com.example.apicall.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerItem(
    shimmerSpeed: Int = 700,
    shimmerColor: Color = Color.LightGray//.copy(alpha = 0.6f)
) {
    val shimmerTransition = rememberInfiniteTransition()
    val shimmerOffset by shimmerTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = shimmerSpeed, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val density = LocalDensity.current

    val shimmerBrush = remember(density) {
        Brush.horizontalGradient(
            colors = listOf(
                Color.Gray.copy(alpha = 0.3f),
                shimmerColor,
                Color.Gray.copy(alpha = 0.3f)
            ),
            startX = shimmerOffset * 300f * density.density,
            endX = shimmerOffset * 600f * density.density
        )
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(250.dp)
            .background(shimmerBrush)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(shimmerBrush)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(20.dp)
                .background(shimmerBrush)
        )

        Spacer(modifier = Modifier.height(8.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(20.dp)
                .background(shimmerBrush)
        )
    }
}
