package com.maxidev.newsyork.homenews.domain.repository

import com.maxidev.newsyork.homenews.domain.model.NewsWire

interface NewsWireRepository {

    suspend fun getNewsWireStream(): List<NewsWire>
}