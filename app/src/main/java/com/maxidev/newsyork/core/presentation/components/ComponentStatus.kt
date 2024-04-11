package com.maxidev.newsyork.core.presentation.components

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComponentStatus(
    modifier: Modifier = Modifier,
    @RawRes animationImage: Int,
    @StringRes text: Int?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ComponentLottie(
            animatedImage = animationImage
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = if (text == null) "" else stringResource(id = text),
            fontSize = 22.sp
        )
    }
}