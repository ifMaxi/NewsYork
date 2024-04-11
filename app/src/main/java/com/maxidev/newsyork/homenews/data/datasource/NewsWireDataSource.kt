package com.maxidev.newsyork.homenews.data.datasource

import com.maxidev.newsyork.core.data.remote.NyApi
import com.maxidev.newsyork.homenews.domain.mappers.toNewsWire
import com.maxidev.newsyork.homenews.domain.model.NewsWire
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsWireDataSource @Inject constructor(
    private val nyApi: NyApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun fetchNwArticles(): List<NewsWire> =
        withContext(ioDispatcher) {
            nyApi.getTimeNewsWire().toNewsWire()
        }
}