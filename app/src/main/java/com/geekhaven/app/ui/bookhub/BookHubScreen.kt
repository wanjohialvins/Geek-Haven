package com.geekhaven.app.ui.bookhub

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.geekhaven.app.data.local.entity.BookEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookHubScreen(
    bookId: Long, // Passed but handled by VM via SavedStateHandle mainly
    onNavigateBack: () -> Unit,
    viewModel: BookHubViewModel = hiltViewModel()
) {
    val book by viewModel.book.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Book Hub") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (book == null) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                BookDetailContent(book = book!!)
            }
        }
    }
}

@Composable
fun BookDetailContent(book: BookEntity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cover Placeholder
        Box(
            modifier = Modifier
                .size(120.dp, 180.dp)
                .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Book, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer, modifier = Modifier.size(48.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = book.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (book.authors.isNotEmpty()) {
            Text(
                text = "by ${book.authors.joinToString(", ")}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        if (!book.memoryAnchor.isNullOrBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiaryContainer, MaterialTheme.shapes.small)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "Last Memory Anchor",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = book.memoryAnchor,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        } else {
             // Maybe show a button to add a note?
             // For now, keeping it clean as per "Nature" theme.
        }

        // Series Info
        if (!book.series.isNullOrBlank()) {
             Text(text = "Series: ${book.series}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
             Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Format Actions
        Text(text = "Available Formats", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))
        
        Row {
            if (book.hasPdf) {
                Button(onClick = { /* Open PDF Reader */ }) {
                    Text("Read PDF")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            if (book.hasEpub) {
                Button(onClick = { /* Open EPUB Reader */ }) {
                    Text("Read EPUB")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            if (book.hasAudio) {
                Button(onClick = { /* Open Audio Player */ }) {
                    Text("Listen")
                }
            }
        }
    }
}
