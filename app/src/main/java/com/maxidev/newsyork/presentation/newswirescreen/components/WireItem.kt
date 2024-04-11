package com.maxidev.newsyork.presentation.newswirescreen.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.maxidev.newsyork.presentation.components.ComponentCoil
import com.maxidev.newsyork.ui.theme.NewsYorkTheme

@Composable
fun WireItem(
    modifier: Modifier = Modifier,
    image: String?,
    title: String?,
    abstract: String?,
    byLine: String?,
    url: String?
) {
    val context = LocalContext.current
    val browserIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )

    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp)
            .clip(RoundedCornerShape(5))
            .clickable {
                startActivity(context, browserIntent, null)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            ImageItem(image = image)
            DescriptionsItem(
                title = title,
                abstract = abstract,
                byLine = byLine
            )
        }
    }
}

@Composable
private fun ImageItem(
    modifier: Modifier = Modifier,
    image: String?
) {
    ComponentCoil(
        image = image,
        contentScale = ContentScale.Crop,
        size = DpSize(400.dp, 300.dp),
        modifier = modifier
            .clip(RoundedCornerShape(5))
    )
}

@Composable
private fun DescriptionsItem(
    modifier: Modifier = Modifier,
    title: String?,
    abstract: String?,
    byLine: String?
) {
    val justify = TextAlign.Justify

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = justify
        )
        Text(
            text = abstract ?: "",
            textAlign = justify
        )
        Text(
            text = byLine ?: "",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview
@Composable
private fun WireItemPreview() {
    NewsYorkTheme {
        WireItem(
            image = "Image",
            title = "Lorem impsum title",
            abstract = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            byLine = "Lorem bylINE",
            url = "url"
        )
    }
}