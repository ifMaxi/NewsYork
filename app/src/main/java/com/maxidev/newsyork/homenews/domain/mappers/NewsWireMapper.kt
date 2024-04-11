package com.maxidev.newsyork.homenews.domain.mappers

import com.maxidev.newsyork.homenews.data.remote.model.NewsWireDTO
import com.maxidev.newsyork.homenews.domain.model.NewsWire

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