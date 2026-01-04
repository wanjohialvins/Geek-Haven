package com.geekhaven.app.domain.repository

interface SearchRepository {
    suspend fun searchWeb(query: String): List<String>
}
