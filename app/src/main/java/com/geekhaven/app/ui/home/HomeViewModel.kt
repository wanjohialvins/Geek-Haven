package com.geekhaven.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekhaven.app.data.local.entity.BookEntity
import com.geekhaven.app.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    bookRepository: BookRepository
) : ViewModel() {

    // Ideally, Repository should expose specific queries. 
    // For MVP, we filter the main flow.
    private val allBooks = bookRepository.getAllBooks()

    val recentBooks: StateFlow<List<BookEntity>> = allBooks
        .map { list -> list.take(5) } // Simple "Recent" (since DAO orders by lastReadTime/desc)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
