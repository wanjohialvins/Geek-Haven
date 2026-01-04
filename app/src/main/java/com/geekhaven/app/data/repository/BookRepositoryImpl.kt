package com.geekhaven.app.data.repository

import com.geekhaven.app.data.local.dao.BookDao
import com.geekhaven.app.data.local.entity.BookEntity
import com.geekhaven.app.data.remote.api.GoogleBooksApi
import com.geekhaven.app.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
    private val api: GoogleBooksApi
) : BookRepository {

    override fun getAllBooks(): Flow<List<BookEntity>> = bookDao.getAllBooks()

    override suspend fun getBookById(id: Long): BookEntity? = bookDao.getBookById(id)

    override suspend fun insertBook(book: BookEntity): Long = bookDao.insertBook(book)

    override suspend fun deleteBook(book: BookEntity) = bookDao.deleteBook(book)

    override suspend fun searchRemoteBooks(query: String, apiKey: String): List<BookEntity> {
        return try {
            val response = api.searchBooks(query = query, apiKey = apiKey)
            response.items?.map { item ->
                BookEntity(
                    googleBookId = item.id,
                    title = item.volumeInfo.title,
                    authors = item.volumeInfo.authors ?: emptyList(),
                    description = item.volumeInfo.description,
                    publishedDate = item.volumeInfo.publishedDate,
                    coverUrl = item.volumeInfo.imageLinks?.thumbnail?.replace("http:", "https:"),
                    isbn = item.volumeInfo.industryIdentifiers?.firstOrNull()?.identifier // simplified
                )
            } ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
