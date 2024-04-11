package com.maxidev.newsyork.di

import com.maxidev.newsyork.data.datasource.NewsWireDataSource
import com.maxidev.newsyork.data.datasource.TopStoriesDataSource
import com.maxidev.newsyork.data.remote.NyApi
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
    fun providesNewsWireDataSource(nyApi: NyApi): NewsWireDataSource =
        NewsWireDataSource(nyApi)

    @Provides
    @Singleton
    fun providesTopStoriesDataSource(nyApi: NyApi): TopStoriesDataSource =
        TopStoriesDataSource(nyApi)
}