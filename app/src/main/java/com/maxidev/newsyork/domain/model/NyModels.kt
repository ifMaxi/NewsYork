package com.maxidev.newsyork.domain.model

data class NewsWire(
    val slugName: String?,
    val title: String?,
    val abstract: String?,
    val byLine: String?,
    val url: String?,
    val multimedia: String?
)

data class ArtSearch(
    val multimedia: String?,
    val id: String?,
    val headline: String?,
    val url: String?,
    val publishDate: String?
)

data class TopStories(
    val title: String?,
    val abstract: String?,
    val byLine: String?,
    val url: String?,
    val multimedia: String?
)