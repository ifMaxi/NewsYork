package com.maxidev.newsyork.topnews.presentation

import com.maxidev.newsyork.topnews.domain.model.TopStories

sealed interface StoriesResponseStat {
    data class Success(val onSuccess: List<TopStories>): StoriesResponseStat
    data class Error(val onError: Exception): StoriesResponseStat
    data object Loading: StoriesResponseStat
}