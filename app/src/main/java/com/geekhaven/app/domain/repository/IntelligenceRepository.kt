package com.geekhaven.app.domain.repository

interface IntelligenceRepository {
    suspend fun interpret(prompt: String): String
}
