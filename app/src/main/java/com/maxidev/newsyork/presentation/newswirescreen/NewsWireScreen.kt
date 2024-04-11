package com.maxidev.newsyork.presentation.newswirescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.newsyork.R
import com.maxidev.newsyork.domain.model.NewsWire
import com.maxidev.newsyork.presentation.components.ComponentStatus
import com.maxidev.newsyork.presentation.newswirescreen.components.WireItem

@Composable
fun NewsWireScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsWireViewModel = hiltViewModel()
) {
    val newsWire by viewModel.nwResponseStat.collectAsStateWithLifecycle()

//    Scaffold(
//        topBar = {
//
//        }
//    ) { paddingValues ->
//
//    }
    WireStatus(
        modifier = modifier,
            //.padding(paddingValues),
        nwResponseStat = newsWire
    )
}

@Composable
private fun WireStatus(
    modifier: Modifier = Modifier,
    nwResponseStat: NwResponseStat
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
            nwModel = nwResponseStat.onSuccess
        )
    }
}

@Composable
private fun NyContent(
    modifier: Modifier = Modifier,
    nwModel: List<NewsWire>,
    lazyListState: LazyListState = rememberLazyListState()
) {
    val nwRemember = remember(nwModel) { nwModel }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        state = lazyListState,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        items(
            items = nwRemember,
            key = { it.slugName!! },
            contentType = { it.slugName }
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