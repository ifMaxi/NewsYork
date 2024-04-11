package com.maxidev.newsyork.topnews.data.repository

import com.maxidev.newsyork.topnews.data.datasource.TopStoriesDataSource
import com.maxidev.newsyork.topnews.domain.model.TopStories
import com.maxidev.newsyork.topnews.domain.repository.TopStoriesRepository
import javax.inject.Inject

class TopStoriesRepositoryImpl @Inject constructor(
    private val dataSource: TopStoriesDataSource
): TopStoriesRepository {

    override suspend fun getTopStoriesStream(section: String): List<TopStories> =
        dataSource.fetchStories(section)
}