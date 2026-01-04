package com.geekhaven.app.di

import com.geekhaven.app.data.repository.BookRepositoryImpl
import com.geekhaven.app.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ): BookRepository

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        impl: com.geekhaven.app.data.repository.SearchRepositoryImpl
    ): com.geekhaven.app.domain.repository.SearchRepository

    @Binds
    @Singleton
    abstract fun bindIntelligenceRepository(
        impl: com.geekhaven.app.data.repository.IntelligenceRepositoryImpl
    ): com.geekhaven.app.domain.repository.IntelligenceRepository
}
