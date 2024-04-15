package com.maxidev.newsyork.homenews.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.newsyork.R
import com.maxidev.newsyork.core.presentation.components.ComponentStatus
import com.maxidev.newsyork.core.presentation.components.ContentListItem
import com.maxidev.newsyork.core.presentation.components.ScrollToTopButton
import com.maxidev.newsyork.homenews.domain.model.NewsWire
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NewsWireScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsWireViewModel,

) {
    val newsWire by viewModel.nwResponseStat.collectAsStateWithLifecycle()

    WireStatus(
        modifier = modifier,
        nwResponseStat = newsWire,
        onRefresh = { viewModel.fetchedNewsWire() }
    )
}

@Composable
private fun WireStatus(
    modifier: Modifier = Modifier,
    nwResponseStat: NwResponseStat,
    onRefresh: () -> Unit
) {
    when (nwResponseStat) {
        is NwResponseStat.Error -> ComponentStatus(
            animationImage = R.raw.net_lose,
            text = R.string.lost_connection
        )
        NwResponseStat.Loading -> ComponentStatus(
            animationImage = R.raw.load_lines,
            text = null
        )
        is NwResponseStat.Success -> NyContent(
            modifier = modifier,
            nwModel = nwResponseStat.onSuccess,
            onRefresh = onRefresh
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun NyContent(
    modifier: Modifier = Modifier,
    nwModel: List<NewsWire>,
    onRefresh: () -> Unit
) {
    val lazyListState: LazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val pullState = rememberPullToRefreshState()
    val newWire = remember(nwModel) { nwModel }
    val showButton by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0
        }
    }

    if (pullState.isRefreshing) {
        LaunchedEffect(true) {
            delay(1500)
            onRefresh()
            pullState.endRefresh()
        }
    }

    Box(
        modifier = modifier
            .nestedScroll(pullState.nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            state = lazyListState,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (!pullState.isRefreshing) {
                items(
                    items = newWire,
                    key = { it.slugName!! },
                    contentType = { it.slugName }
                ) { item ->
                    item.let { param ->
                        ContentListItem(
                            modifier = Modifier
                                .animateItemPlacement(tween(250)),
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

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullState
        )
    }

    AnimatedVisibility(
        visible = showButton,
        enter = slideInVertically() + expandVertically() + fadeIn(),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        ScrollToTopButton(
            modifier = Modifier
                .padding(top = 20.dp),
            onClick = {
                scope.launch {
                    lazyListState.animateScrollToItem(index = 0)
                }
            }
        )
    }
}