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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Search
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
    onNavigateToSearch: () -> Unit,
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
                // Search Button (Big)
                androidx.compose.material3.Button(onClick = onNavigateToSearch) {
                    Text("Discover Books")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Go to Library to add books.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            // Dashboard
            
            // Search Bar Entry (Fake)
            androidx.compose.material3.Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp)
                    .clickable { onNavigateToSearch() },
                shape = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                tonalElevation = 4.dp
            ) {
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                   Icon(androidx.compose.material.icons.Icons.Default.Search, contentDescription = null)
                   Spacer(modifier = Modifier.width(16.dp))
                   Text("Search Books...", style = MaterialTheme.typography.bodyLarge)
                }
            }

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
