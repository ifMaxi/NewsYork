package com.maxidev.newsyork.topnews.di

import com.maxidev.newsyork.core.data.remote.NyApi
import com.maxidev.newsyork.topnews.data.datasource.TopStoriesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesTopStoriesDataSource(nyApi: NyApi): TopStoriesDataSource =
        TopStoriesDataSource(nyApi)
}