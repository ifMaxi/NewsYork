package com.maxidev.newsyork.homenews.di

import com.maxidev.newsyork.core.data.remote.NyApi
import com.maxidev.newsyork.homenews.data.datasource.NewsWireDataSource
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
}