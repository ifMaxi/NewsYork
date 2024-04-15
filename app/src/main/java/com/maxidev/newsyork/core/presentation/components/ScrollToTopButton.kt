package com.maxidev.newsyork.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScrollToTopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        FilledIconButton(
            modifier = Modifier,
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowUp,
                contentDescription = null
            )
        }
    }
}