package com.maxidev.newsyork.core.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpSize
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.maxidev.newsyork.R
import kotlinx.coroutines.Dispatchers

@Composable
fun ComponentCoil(
    modifier: Modifier = Modifier,
    image: String?,
    contentScale: ContentScale,
    size: DpSize
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(image)
        .crossfade(true)
        .fetcherDispatcher(Dispatchers.IO)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .networkCachePolicy(CachePolicy.ENABLED)
        .allowHardware(true)
        .error(R.drawable.outline_broken_image_24) // TODO: Change error image
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .wrapContentSize()
            .size(size)
            .clip(RoundedCornerShape(5))
    )
}