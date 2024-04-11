package com.maxidev.newsyork.search.domain.mappers

import com.maxidev.newsyork.search.data.remote.model.ArticleSearchDTO
import com.maxidev.newsyork.search.domain.model.ArtSearch

/**
 * Converts remote model in external model.
 */
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