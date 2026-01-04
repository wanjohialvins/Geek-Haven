package com.geekhaven.app.data.remote.api

import com.geekhaven.app.data.remote.dto.GoogleBooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20,
        @Query("key") apiKey: String
    ): GoogleBooksResponse

    @GET("volumes")
    suspend fun getBookByIsbn(
        @Query("q") isbnQuery: String, // format: "isbn:978..."
        @Query("key") apiKey: String
    ): GoogleBooksResponse
}
