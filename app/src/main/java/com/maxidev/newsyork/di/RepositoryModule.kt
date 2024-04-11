package com.maxidev.newsyork.di

import com.maxidev.newsyork.data.repository.ArtSearchRepositoryImpl
import com.maxidev.newsyork.data.repository.NewsWireRepositoryImpl
import com.maxidev.newsyork.data.repository.TopStoriesRepositoryImpl
import com.maxidev.newsyork.domain.repository.ArtSearchRepository
import com.maxidev.newsyork.domain.repository.NewsWireRepository
import com.maxidev.newsyork.domain.repository.TopStoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsNewWireRepository(
        newsWireRepositoryImpl: NewsWireRepositoryImpl
    ): NewsWireRepository

    @Binds
    abstract fun bindsArtSearchRepository(
        artSearchRepositoryImpl: ArtSearchRepositoryImpl
    ): ArtSearchRepository

    @Binds
    abstract fun bindsTopStoriesRepository(
        topStoriesRepositoryImpl: TopStoriesRepositoryImpl
    ): TopStoriesRepository
}