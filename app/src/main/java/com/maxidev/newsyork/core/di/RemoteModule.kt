package com.maxidev.newsyork.core.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxidev.newsyork.core.data.remote.CacheInterceptor
import com.maxidev.newsyork.core.data.remote.Interceptor
import com.maxidev.newsyork.core.data.remote.NyApi
import com.maxidev.newsyork.core.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun providesRetrofit(
        @ApplicationContext context: Context
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }
        val cache = Cache(
            directory = File(context.cacheDir, "http_cache"),
            maxSize = 50L * 1024L * 1024L
        )
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(Interceptor())
            .addNetworkInterceptor(CacheInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): NyApi =
        retrofit.create(NyApi::class.java)
}