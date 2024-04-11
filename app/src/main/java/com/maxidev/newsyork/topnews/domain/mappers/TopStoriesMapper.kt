package com.maxidev.newsyork.topnews.domain.mappers

import com.maxidev.newsyork.topnews.data.remote.model.TopStoriesDTO
import com.maxidev.newsyork.topnews.domain.model.TopStories

/**
 * Converts remote model in external model.
 */
fun TopStoriesDTO.toTopStories(): List<TopStories> {
    return this.results?.map { tops ->
        TopStories(
            title = tops?.title,
            abstract = tops?.abstract,
            byLine = tops?.byline,
            url = tops?.url,
            multimedia = tops?.multimedia?.get(1)?.url
        )
    } ?: emptyList()
}