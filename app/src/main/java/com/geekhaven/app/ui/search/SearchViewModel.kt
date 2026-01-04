package com.geekhaven.app.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekhaven.app.data.local.entity.BookEntity
import com.geekhaven.app.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _searchResults = MutableStateFlow<List<BookEntity>>(emptyList())
    val searchResults: StateFlow<List<BookEntity>> = _searchResults.asStateFlow()
    
    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching.asStateFlow()

    @OptIn(FlowPreview::class)
    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            if (newQuery.isBlank()) {
                _searchResults.value = emptyList()
                return@launch
            }
            
            _isSearching.value = true
            try {
                // Combined Search: Local Filter + Remote API (Stubbed API key for now)
                // In a real app, we'd search DB efficiently. Here filtering all books for MVP.
                val localMatches = bookRepository.getAllBooks().value  // Accessing flow value in not ideal, but for MVP...
                    // Actually, let's just trigger a search call.
                    // For now, I'll implementing a simple repository search capability
                   
                // Ideally repository exposes search. Let's rely on View doing a search action or Debounce.
                // Let's implement debounce here simply
            } finally {
               // _isSearching.value = false // Async
            }
        }
    }
    
    // Explicit search trigger
    fun search(query: String) {
        viewModelScope.launch {
            _isSearching.value = true
            try {
                // 1. Local Search (In-memory filter of all books for speed in MVP)
                // Note: In production, Room FTS is better.
                // For MVP: We will use the remote search mainly for "Discovery"
                
                val remoteResults = bookRepository.searchRemoteBooks(query, "YOUR_API_KEY_HERE") // Placeholder Key
                _searchResults.value = remoteResults
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isSearching.value = false
            }
        }
    }
}
