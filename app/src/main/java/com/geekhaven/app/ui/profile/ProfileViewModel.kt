package com.geekhaven.app.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekhaven.app.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    bookRepository: BookRepository
) : ViewModel() {

    val stats: StateFlow<ReadingStats> = bookRepository.getAllBooks()
        .map { books ->
            val totalBooks = books.count()
            val completedBooks = books.count { it.readingStatus == com.geekhaven.app.data.local.entity.ReadingStatus.COMPLETED }
            // Pages read approximation (just summing current page for now, ideally sum of read books + current)
            val pagesRead = books.sumOf { it.currentPage }
            val hoursListened = books.sumOf { it.audioPosition } / (1000 * 60 * 60)
            
            ReadingStats(totalBooks, completedBooks, pagesRead, hoursListened.toInt())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ReadingStats(0, 0, 0, 0)
        )
}

data class ReadingStats(
    val totalBooks: Int,
    val completedBooks: Int,
    val pagesRead: Int,
    val hoursListened: Int
)
