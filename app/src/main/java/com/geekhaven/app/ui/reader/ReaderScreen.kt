package com.geekhaven.app.ui.reader

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.filled.Headphones


@Composable
fun ReaderScreen(
    bookId: Long,
    format: String, // "pdf", "epub", "audio"
    onNavigateBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (format.lowercase()) {
            "pdf" -> PdfReader(bookId, onNavigateBack)
            "audio" -> AudioPlayer(bookId, onNavigateBack)
            "epub" -> Text(text = "EPUB Reader Coming Soon")
            else -> Text(text = "Unknown Format: $format")
        }
    }
}

@Composable
fun PdfReader(
    bookId: Long,
    onNavigateBack: () -> Unit,
    viewModel: PdfViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(bookId) {
        viewModel.loadPdf(bookId)
    }

    when (val s = state) {
        is PdfReaderState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is PdfReaderState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${s.message}")
            }
        }
        is PdfReaderState.Ready -> {
            val pagerState = rememberPagerState(
                initialPage = s.initialPage,
                pageCount = { s.pageCount }
            )
            
            // Track page changes
            LaunchedEffect(pagerState.currentPage) {
                viewModel.saveProgress(pagerState.currentPage)
            }
            
            // Simple Pager for now (Nature: "Focus on Content")
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize().background(Color.White) // Paper background
            ) { pageIndex ->
                PdfPage(index = pageIndex, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun AudioPlayer(
    bookId: Long,
    onNavigateBack: () -> Unit,
    viewModel: AudioViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(bookId) {
        viewModel.loadAudio(bookId)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val s = state) {
            is AudioPlayerState.Loading -> CircularProgressIndicator()
            is AudioPlayerState.Error -> Text("Error: ${s.message}")
            is AudioPlayerState.Playing -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Now Playing", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Simple Icon
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Headphones,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Text(text = "${formatTime(s.position)} / ${formatTime(s.duration)}")
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Button(onClick = { viewModel.togglePlayPause() }) {
                        Text(text = "Play / Pause")
                    }
                }
            }
        }
    }
}

fun formatTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}
