package com.maxidev.newsyork.domain.repository

import com.maxidev.newsyork.domain.model.NewsWire

interface NewsWireRepository {

    suspend fun getNewsWireStream(): List<NewsWire>
}