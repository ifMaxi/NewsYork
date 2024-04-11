package com.maxidev.newsyork.domain.repository

import com.maxidev.newsyork.domain.model.TopStories

interface TopStoriesRepository {

    suspend fun getTopStoriesStream(section: String): List<TopStories>
}