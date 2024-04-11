package com.maxidev.newsyork.search.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.newsyork.core.data.remote.NyApi
import com.maxidev.newsyork.search.domain.mappers.toArtSearch
import com.maxidev.newsyork.search.domain.model.ArtSearch
import retrofit2.HttpException
import java.io.IOException

class ArtSearchPagingSource(
    private val backend: NyApi,
    private val query: String
): PagingSource<Int, ArtSearch>() {
    override fun getRefreshKey(state: PagingState<Int, ArtSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtSearch> {
        return try {
            val nextPage = params.key ?: 1
            val response = backend.getSearchedArticles(q = query, page = nextPage)

            LoadResult.Page(
                data = response.toArtSearch(),
                prevKey = if (nextPage == 1) null else nextPage.plus(1),
                nextKey = if (response.toArtSearch().isEmpty()) null else nextPage.plus(1 )
            )
        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }
}