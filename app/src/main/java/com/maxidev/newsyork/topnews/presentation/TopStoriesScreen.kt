package com.maxidev.newsyork.topnews.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.newsyork.R
import com.maxidev.newsyork.core.presentation.components.ComponentStatus
import com.maxidev.newsyork.core.utils.TopStoriesUtils
import com.maxidev.newsyork.homenews.presentation.components.WireItem
import com.maxidev.newsyork.topnews.domain.model.TopStories

@Composable
fun TopStoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: TopStoriesViewModel = hiltViewModel()
) {
    val state by viewModel.storiesResponseStat.collectAsStateWithLifecycle()
    val topics = TopStoriesUtils.topList
    var tabIndex by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchedStories("arts")
    }

    Scaffold(
        topBar = {
            ScrollableTabRow(
                selectedTabIndex = tabIndex
            ) {
                topics.forEachIndexed { index, s ->
                    Tab(
                        text = {
                            Text(text = s, maxLines = 1)
                        },
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                            viewModel.fetchedStories(stories = s)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        StoryResponseStat(
            modifier = Modifier
                .padding(paddingValues),
            status = state
        )
    }
}

@Composable
private fun StoryResponseStat(
    modifier: Modifier = Modifier,
    status: StoriesResponseStat
) {
    when (status) {
        is StoriesResponseStat.Error -> ComponentStatus(
            animationImage = R.raw.net_lose,
            text = R.string.lost_connection
        )
        StoriesResponseStat.Loading -> ComponentStatus(
            animationImage = R.raw.load_lines,
            text = null
        )
        is StoriesResponseStat.Success -> StoryContent(
            modifier = modifier,
            model = status.onSuccess
        )
    }
}

@Composable
private fun StoryContent(
    modifier: Modifier = Modifier,
    model: List<TopStories>
) {
    val lazyState: LazyListState = rememberLazyListState()
    val remModel = remember(model) { model }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        state = lazyState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        items(
            items = remModel,
            key = { it.title!! },
            contentType = { it.title }
        ) { item ->
            item.let { param ->
                WireItem(
                    image = param.multimedia,
                    title = param.title,
                    abstract = param.abstract,
                    byLine = param.byLine,
                    url = param.url
                )
            }
            HorizontalDivider()
        }
    }
}

// ---------------------------------------------------------------------------------

@Composable
private fun NameList(
    onClick: (String) -> Unit
) {
    val listName = TopStoriesUtils.topList

    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp)) {
        items(items = listName) { item ->
            StoryNameItem(
                name = item,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun StoryNameItem(
    name: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(width = 200.dp, height = 100.dp)
            .clickable { onClick(name) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}