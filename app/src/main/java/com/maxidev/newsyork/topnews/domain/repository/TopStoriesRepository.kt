package com.maxidev.newsyork.topnews.domain.repository

import com.maxidev.newsyork.topnews.domain.model.TopStories

interface TopStoriesRepository {

    suspend fun getTopStoriesStream(section: String): List<TopStories>
}