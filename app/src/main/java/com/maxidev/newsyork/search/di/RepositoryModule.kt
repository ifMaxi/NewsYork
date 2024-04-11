package com.maxidev.newsyork.search.di

import com.maxidev.newsyork.search.data.repository.ArtSearchRepositoryImpl
import com.maxidev.newsyork.search.domain.repository.ArtSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsArtSearchRepository(
        artSearchRepositoryImpl: ArtSearchRepositoryImpl
    ): ArtSearchRepository
}