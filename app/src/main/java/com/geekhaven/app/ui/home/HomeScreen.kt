package com.geekhaven.app.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.geekhaven.app.data.local.entity.BookEntity
import com.geekhaven.app.ui.library.BookItem

@Composable
fun HomeScreen(
    onNavigateToLibrary: () -> Unit,
    onNavigateToBook: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val recentBooks by viewModel.recentBooks.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (recentBooks.isEmpty()) {
            // Empty State
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Welcome to Geek Haven", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Go to Library to add books.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            // Dashboard
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    Text(
                        text = "Continue Reading",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(recentBooks) { book ->
                    // Reusing BookItem for now, though usually Home cards are different
                     BookItem(book = book, onClick = { onNavigateToBook(book.id) })
                }
                
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Recommended for You",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                
                val recommendedBooks by viewModel.recommendedBooks.collectAsState()
                if (recommendedBooks.isEmpty()) {
                     item { Text("Add more books to get recommendations!", style = MaterialTheme.typography.bodyMedium) }
                } else {
                    items(recommendedBooks) { book ->
                        BookItem(book = book, onClick = { onNavigateToBook(book.id) })
                    }
                }
            }
        }
    }
}
