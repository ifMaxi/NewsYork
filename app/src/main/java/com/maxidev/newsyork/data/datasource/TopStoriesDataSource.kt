package com.maxidev.newsyork.data.datasource

import com.maxidev.newsyork.data.remote.NyApi
import com.maxidev.newsyork.domain.mappers.toTopStories
import com.maxidev.newsyork.domain.model.TopStories
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopStoriesDataSource @Inject constructor(
    private val nyApi: NyApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun fetchStories(section: String): List<TopStories> =
        withContext(ioDispatcher) {
            nyApi.getTopStories(section).toTopStories()
        }
}