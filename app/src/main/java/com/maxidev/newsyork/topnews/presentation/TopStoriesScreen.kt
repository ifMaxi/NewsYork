package com.maxidev.newsyork.topnews.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.newsyork.R
import com.maxidev.newsyork.core.presentation.components.ComponentStatus
import com.maxidev.newsyork.core.presentation.components.ContentListItem
import com.maxidev.newsyork.core.presentation.components.ScrollToTopButton
import com.maxidev.newsyork.core.ui.theme.josefinSans
import com.maxidev.newsyork.core.utils.TopStoriesUtils
import com.maxidev.newsyork.topnews.domain.model.TopStories
import kotlinx.coroutines.launch

@Composable
fun TopStoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: TopStoriesViewModel
) {
    val state by viewModel.storiesResponseStat.collectAsStateWithLifecycle()
    val topics = TopStoriesUtils.topList
    var tabIndex by remember { mutableIntStateOf(0) }

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
                            Text(
                                text = s,
                                maxLines = 1,
                                fontFamily = josefinSans
                            )
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
            modifier = modifier
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
            text = R.string.lost_connection,
            size = DpSize(width = 100.dp, height = 100.dp)
        )
        StoriesResponseStat.Loading -> ComponentStatus(
            animationImage = R.raw.load_lines,
            text = null,
            size = DpSize(width = 380.dp, height = 380.dp)
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
    val scope = rememberCoroutineScope()
    val showButton by remember {
        derivedStateOf {
            lazyState.firstVisibleItemIndex > 0
        }
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
            items = remModel,
            key = { it.title!! },
            contentType = { it.title }
        ) { item ->
            item.let { param ->
                ContentListItem(
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

    AnimatedVisibility(
        visible = showButton,
        enter = slideInVertically() + expandVertically() + fadeIn(),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        ScrollToTopButton(
            modifier = Modifier
                .padding(top = 60.dp),
            onClick = {
                scope.launch {
                    lazyState.animateScrollToItem(index = 0)
                }
            }
        )
    }
}