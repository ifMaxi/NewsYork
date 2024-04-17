package com.maxidev.newsyork.core.presentation.components

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ComponentLottie(
    modifier: Modifier = Modifier,
    @RawRes animatedImage: Int,
    size: DpSize
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(animatedImage)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        modifier = modifier
            .size(size),
        composition = composition,
        progress = { progress }
    )
}