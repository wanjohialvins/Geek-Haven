package com.geekhaven.app.di

import com.geekhaven.app.data.remote.api.GoogleBooksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGoogleBooksApi(retrofit: Retrofit): GoogleBooksApi {
        return retrofit.create(GoogleBooksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWebSearchApi(retrofit: Retrofit): WebSearchApi {
        return retrofit.create(WebSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeminiApi(retrofit: Retrofit): GeminiApi {
        // Note: Real Gemini API URL might differ (generativelanguage.googleapis.com), 
        // requiring a separate Retrofit instance or @Url. 
        // For now, using the same base or assuming generic handling.
        return retrofit.create(GeminiApi::class.java)
    }
}
