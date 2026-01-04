package com.geekhaven.app.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

// Placeholder for a Custom Search JSON API or similar
interface WebSearchApi {
    @GET("customsearch/v1")
    suspend fun search(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("cx") cx: String
    ): WebSearchResponse
}

data class WebSearchResponse(
    val items: List<WebSearchResult>?
)

data class WebSearchResult(
    val title: String,
    val link: String,
    val snippet: String?
)
