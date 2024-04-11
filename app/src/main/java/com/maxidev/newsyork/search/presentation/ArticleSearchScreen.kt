package com.maxidev.newsyork.search.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.maxidev.newsyork.R
import com.maxidev.newsyork.core.presentation.components.ComponentCoil
import com.maxidev.newsyork.core.presentation.components.ComponentStatus
import com.maxidev.newsyork.core.utils.dateTimeUtils
import com.maxidev.newsyork.search.domain.model.ArtSearch
import com.maxidev.newsyork.search.presentation.components.ArtSearchBar
import kotlinx.coroutines.launch

@Composable
fun ArticleSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: ArtSearchViewModel = hiltViewModel()
) {
    val query by viewModel.search
    val searchedArticles = viewModel.searchedArt.collectAsLazyPagingItems()
    var isActive by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            ArtSearchBar(
                value = query,
                onValueChange = viewModel::onSearchChange,
                onSearch = {
                    scope.launch {
                        if (query.isEmpty()) {
                            isActive = false
                        } else {
                            isActive = false
                            viewModel.artSearched(it)
                        }
                    }
                    focusManager.clearFocus()
                },
                active = isActive,
                onActiveChange = {
                    isActive = false
                },
                onClearText = {
                    viewModel.onSearchChange("")
                    focusManager.clearFocus()
                }
            )
        }
    ) { paddingValues ->
        ArtContent(
            modifier = modifier
                .padding(paddingValues),
            items = searchedArticles
        )
    }
}

@Composable
private fun ArtContent(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<ArtSearch>
) {
    val lazyState: LazyListState = rememberLazyListState()

    if (items.loadState.refresh is LoadState.Loading) {
        ComponentStatus(
            animationImage = R.raw.load_lines,
            text = null
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        state = lazyState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        items(
            count = items.itemCount,
            key = items.itemKey { it.id!! },
            contentType = items.itemContentType { it.id }
        ) { article ->
            items[article]?.let {
                ArtItem(
                    multimedia = it.multimedia,
                    headline = it.headline,
                    url = it.url,
                    publishedDate = it.publishDate
                )
            }
        }
    }

    if (items.loadState.refresh is LoadState.Error) {
        ComponentStatus(
            animationImage = R.raw.net_lose,
            text = null
        )
    }
}

@Composable
private fun ArtItem(
    modifier: Modifier = Modifier,
    multimedia: String?,
    headline: String?,
    url: String?,
    publishedDate: String?
) {
    val context = LocalContext.current
    val browserIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    val dateTime = dateTimeUtils(publishedDate ?: "")

    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(5))
            .clickable {
                ContextCompat.startActivity(context, browserIntent, null)
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ComponentCoil(
            image = "https://www.nytimes.com/$multimedia",
            contentScale = ContentScale.Crop,
            size = DpSize(100.dp, 100.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(5))
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = headline ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Justify
            )
            Text(text = dateTime)
        }
    }
}