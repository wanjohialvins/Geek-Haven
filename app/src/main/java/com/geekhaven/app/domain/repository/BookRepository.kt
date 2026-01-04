package com.geekhaven.app.domain.repository

import com.geekhaven.app.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getAllBooks(): Flow<List<BookEntity>>
    suspend fun getBookById(id: Long): BookEntity?
    suspend fun insertBook(book: BookEntity): Long
    suspend fun deleteBook(book: BookEntity)
    
    // Remote
    suspend fun searchRemoteBooks(query: String, apiKey: String): List<BookEntity>
    
    // Progress
    suspend fun updateProgress(bookId: Long, page: Int, audioPos: Long, percentage: Int, status: com.geekhaven.app.data.local.entity.ReadingStatus)
}
