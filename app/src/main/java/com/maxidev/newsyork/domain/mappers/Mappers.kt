package com.maxidev.newsyork.domain.mappers

import com.maxidev.newsyork.data.remote.models.ArticleSearchDTO
import com.maxidev.newsyork.data.remote.models.NewsWireDTO
import com.maxidev.newsyork.data.remote.models.TopStoriesDTO
import com.maxidev.newsyork.domain.model.ArtSearch
import com.maxidev.newsyork.domain.model.NewsWire
import com.maxidev.newsyork.domain.model.TopStories

/**
 * Converts remote model in external model.
 */
fun NewsWireDTO.toNewsWire(): List<NewsWire> {
    return this.results?.map { result ->
        NewsWire(
            slugName = result?.slugName,
            title = result?.title,
            abstract = result?.abstract,
            byLine = result?.byline,
            url = result?.url,
            multimedia = result?.multimedia?.getOrNull(2)?.url
        )
    } ?: emptyList()
}

fun ArticleSearchDTO.toArtSearch(): List<ArtSearch> {
    return this.response?.docs?.map { docs ->
        ArtSearch(
            multimedia = docs?.multimedia?.getOrNull(1)?.url,
            id = docs?.id,
            headline = docs?.headline?.main,
            url = docs?.webUrl,
            publishDate = docs?.pubDate
        )
    } ?: emptyList()
}

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