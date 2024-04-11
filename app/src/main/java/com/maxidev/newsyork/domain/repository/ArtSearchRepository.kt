package com.maxidev.newsyork.domain.repository

import androidx.paging.PagingData
import com.maxidev.newsyork.domain.model.ArtSearch
import kotlinx.coroutines.flow.Flow

interface ArtSearchRepository {

    fun artSearchStream(q: String): Flow<PagingData<ArtSearch>>
}