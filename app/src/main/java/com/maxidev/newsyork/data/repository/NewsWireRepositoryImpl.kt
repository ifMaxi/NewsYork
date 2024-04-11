package com.maxidev.newsyork.data.repository

import com.maxidev.newsyork.data.datasource.NewsWireDataSource
import com.maxidev.newsyork.domain.model.NewsWire
import com.maxidev.newsyork.domain.repository.NewsWireRepository
import javax.inject.Inject

class NewsWireRepositoryImpl @Inject constructor(
    private val nwDataSource: NewsWireDataSource
): NewsWireRepository {

    override suspend fun getNewsWireStream(): List<NewsWire> =
        nwDataSource.fetchNwArticles()
}