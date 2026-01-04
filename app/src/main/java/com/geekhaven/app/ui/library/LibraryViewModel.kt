package com.geekhaven.app.ui.library

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekhaven.app.data.local.entity.BookEntity
import com.geekhaven.app.domain.repository.BookRepository
import com.geekhaven.app.domain.usecase.ScanLocalBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val scanLocalBooksUseCase: ScanLocalBooksUseCase
) : ViewModel() {

    // Hot flow of books, updated automatically by Room
    val books: StateFlow<List<BookEntity>> = bookRepository.getAllBooks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun scanDirectory(uri: Uri) {
        viewModelScope.launch {
            try {
                scanLocalBooksUseCase(uri)
            } catch (e: Exception) {
                e.printStackTrace()
                // In a real app, emit error state to UI
            }
        }
    }
}
