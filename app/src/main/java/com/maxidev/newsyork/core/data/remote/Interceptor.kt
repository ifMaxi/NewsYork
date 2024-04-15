package com.maxidev.newsyork.core.data.remote

import com.maxidev.newsyork.BuildConfig
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class Interceptor: Interceptor {
    private val apikey = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url
            .newBuilder()
            .addQueryParameter("api-key", apikey)
            .build()

        original = original
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(original)
    }
}

class CacheInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            //.maxAge(1, TimeUnit.DAYS)
            .minFresh(10, TimeUnit.MINUTES)
            .build()

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}