package com.maxidev.newsyork.data.repository

import com.maxidev.newsyork.data.datasource.TopStoriesDataSource
import com.maxidev.newsyork.domain.model.TopStories
import com.maxidev.newsyork.domain.repository.TopStoriesRepository
import javax.inject.Inject

class TopStoriesRepositoryImpl @Inject constructor(
    private val dataSource: TopStoriesDataSource
): TopStoriesRepository {

    override suspend fun getTopStoriesStream(section: String): List<TopStories> =
        dataSource.fetchStories(section)
}