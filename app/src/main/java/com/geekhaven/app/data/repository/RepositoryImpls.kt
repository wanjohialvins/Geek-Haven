package com.geekhaven.app.data.repository

import com.geekhaven.app.data.remote.api.GeminiApi
import com.geekhaven.app.data.remote.api.WebSearchApi
import com.geekhaven.app.domain.repository.IntelligenceRepository
import com.geekhaven.app.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: WebSearchApi
) : SearchRepository {
    override suspend fun searchWeb(query: String): List<String> {
        // Stub: In production this would call api.search(...)
        return emptyList()
    }
}

class IntelligenceRepositoryImpl @Inject constructor(
    private val api: GeminiApi
) : IntelligenceRepository {
    override suspend fun interpret(prompt: String): String {
        // Stub: In production this would call api.generateContent(...)
        return "AI Interpretation Placeholder"
    }
}
