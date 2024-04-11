package com.maxidev.newsyork.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxidev.newsyork.data.datasource.paging.ArtSearchPagingSource
import com.maxidev.newsyork.data.remote.NyApi
import com.maxidev.newsyork.domain.model.ArtSearch
import com.maxidev.newsyork.domain.repository.ArtSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtSearchRepositoryImpl @Inject constructor(
    private val nyApi: NyApi
): ArtSearchRepository {
    override fun artSearchStream(q: String): Flow<PagingData<ArtSearch>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ArtSearchPagingSource(backend = nyApi, query = q)
            }
        ).flow
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}