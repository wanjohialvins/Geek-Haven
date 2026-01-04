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
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Forward30


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
    var showMemoryDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        showMemoryDialog = true
    }
    
    if (showMemoryDialog) {
        MemoryAnchorDialog(
            onDismiss = {
                showMemoryDialog = false
                onNavigateBack() // Just exit without saving anchor
            },
            onSave = { anchor ->
                showMemoryDialog = false
                viewModel.saveMemoryAnchor(anchor)
                onNavigateBack()
            }
        )
    }

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
    var showMemoryDialog by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        showMemoryDialog = true
    }

    if (showMemoryDialog) {
        MemoryAnchorDialog(
            onDismiss = {
                showMemoryDialog = false
                onNavigateBack()
            },
            onSave = { anchor ->
                showMemoryDialog = false
                viewModel.saveMemoryAnchor(anchor)
                onNavigateBack()
            }
        )
    }

    LaunchedEffect(bookId) {
        viewModel.loadAudio(bookId)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val s = state) {
            is AudioPlayerState.Loading -> CircularProgressIndicator()
            is AudioPlayerState.Error -> Text("Error: ${s.message}")
            is AudioPlayerState.Playing -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(32.dp)
                ) {
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
                    
                    // Progress Slider
                    androidx.compose.material3.Slider(
                        value = s.position.toFloat(),
                        onValueChange = { viewModel.seekTo(it.toLong()) },
                        valueRange = 0f..s.duration.toFloat().coerceAtLeast(1f)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                    ) {
                        Text(text = formatTime(s.position), style = MaterialTheme.typography.bodySmall)
                        Text(text = formatTime(s.duration), style = MaterialTheme.typography.bodySmall)
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Controls
                    Row(verticalAlignment = Alignment.CenterVertically) {
                         // replay 10s
                         androidx.compose.material3.IconButton(onClick = { viewModel.seekTo((s.position - 10000).coerceAtLeast(0)) }) {
                            Icon(androidx.compose.material.icons.Icons.Default.Replay10, "Rewind 10s")
                         }
                         
                         Spacer(modifier = Modifier.width(16.dp))
                    
                        Button(
                            onClick = { viewModel.togglePlayPause() },
                            modifier = Modifier.size(64.dp),
                            shape = androidx.compose.foundation.shape.CircleShape,
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                        ) {
                            Icon(
                                imageVector = if (viewModel.player?.isPlaying == true) androidx.compose.material.icons.Icons.Default.Pause else androidx.compose.material.icons.Icons.Default.PlayArrow,
                                contentDescription = "Play/Pause"
                            )
                        }
                        
                         Spacer(modifier = Modifier.width(16.dp))
                         
                         // forward 30s
                         androidx.compose.material3.IconButton(onClick = { viewModel.seekTo((s.position + 30000).coerceAtMost(s.duration)) }) {
                            Icon(androidx.compose.material.icons.Icons.Default.Forward30, "Forward 30s")
                         }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Speed Toggle
                    var speed by remember { mutableStateOf(1.0f) }
                    TextButton(onClick = {
                        speed = if (speed >= 2.0f) 0.75f else speed + 0.25f
                        viewModel.setPlaybackSpeed(speed)
                    }) {
                        Text("Speed: ${speed}x")
                    }
                }
            }
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
