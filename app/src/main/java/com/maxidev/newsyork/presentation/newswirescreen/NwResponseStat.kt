package com.maxidev.newsyork.presentation.newswirescreen

import com.maxidev.newsyork.domain.model.NewsWire

sealed interface NwResponseStat {
    data class Success(val onSuccess: List<NewsWire>): NwResponseStat
    data class Error(val onError: Exception): NwResponseStat
    data object Loading: NwResponseStat
}