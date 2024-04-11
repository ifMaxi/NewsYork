package com.maxidev.newsyork.homenews.presentation

import com.maxidev.newsyork.homenews.domain.model.NewsWire

sealed interface NwResponseStat {
    data class Success(val onSuccess: List<NewsWire>): NwResponseStat
    data class Error(val onError: Exception): NwResponseStat
    data object Loading: NwResponseStat
}