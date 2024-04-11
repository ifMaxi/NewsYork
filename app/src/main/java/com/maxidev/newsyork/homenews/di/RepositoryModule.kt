package com.maxidev.newsyork.homenews.di

import com.maxidev.newsyork.homenews.data.repository.NewsWireRepositoryImpl
import com.maxidev.newsyork.homenews.domain.repository.NewsWireRepository
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
}