package com.maxidev.newsyork.topnews.di

import com.maxidev.newsyork.topnews.domain.repository.TopStoriesRepository
import com.maxidev.newsyork.topnews.data.repository.TopStoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsTopStoriesRepository(
        topStoriesRepositoryImpl: TopStoriesRepositoryImpl
    ): TopStoriesRepository
}