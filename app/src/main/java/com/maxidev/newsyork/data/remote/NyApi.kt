package com.maxidev.newsyork.data.remote

import com.maxidev.newsyork.data.remote.models.ArticleSearchDTO
import com.maxidev.newsyork.data.remote.models.NewsWireDTO
import com.maxidev.newsyork.data.remote.models.TopStoriesDTO
import com.maxidev.newsyork.utils.Constants.ARTICLE_SEARCH_API
import com.maxidev.newsyork.utils.Constants.TIMES_NEWSWIRE_API
import com.maxidev.newsyork.utils.Constants.TOP_STORIES_API
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NyApi {
    @GET(TIMES_NEWSWIRE_API + "content/{source}/{section}.json")
    suspend fun getTimeNewsWire(
        @Path("source") source: String = PATH_NEWSWIRE_PARAMETER,
        @Path("section") section: String = PATH_NEWSWIRE_PARAMETER,
        @Query("limit") limit: Int = 40
    ): NewsWireDTO

    @GET(ARTICLE_SEARCH_API + "articlesearch.json")
    suspend fun getSearchedArticles(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("sort") sort: String = SORT_BY_NEWEST
    ): ArticleSearchDTO

    @GET("$TOP_STORIES_API{section}.json")
    suspend fun getTopStories(
        @Path("section") section: String
    ): TopStoriesDTO

    companion object {
        const val PATH_NEWSWIRE_PARAMETER = "all"
        const val SORT_BY_NEWEST = "newest"
    }
}