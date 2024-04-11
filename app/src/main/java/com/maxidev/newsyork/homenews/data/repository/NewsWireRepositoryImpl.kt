package com.maxidev.newsyork.homenews.data.repository

import com.maxidev.newsyork.homenews.data.datasource.NewsWireDataSource
import com.maxidev.newsyork.homenews.domain.model.NewsWire
import com.maxidev.newsyork.homenews.domain.repository.NewsWireRepository
import javax.inject.Inject

class NewsWireRepositoryImpl @Inject constructor(
    private val nwDataSource: NewsWireDataSource
): NewsWireRepository {

    override suspend fun getNewsWireStream(): List<NewsWire> =
        nwDataSource.fetchNwArticles()
}