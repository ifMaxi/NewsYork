package com.maxidev.newsyork.presentation.topstoriesscreen

import com.maxidev.newsyork.domain.model.TopStories

sealed interface StoriesResponseStat {
    data class Success(val onSuccess: List<TopStories>): StoriesResponseStat
    data class Error(val onError: Exception): StoriesResponseStat
    data object Loading: StoriesResponseStat
}