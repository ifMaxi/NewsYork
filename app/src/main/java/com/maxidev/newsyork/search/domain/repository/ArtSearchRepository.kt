package com.maxidev.newsyork.search.domain.repository

import androidx.paging.PagingData
import com.maxidev.newsyork.search.domain.model.ArtSearch
import kotlinx.coroutines.flow.Flow

interface ArtSearchRepository {

    fun artSearchStream(q: String): Flow<PagingData<ArtSearch>>
}