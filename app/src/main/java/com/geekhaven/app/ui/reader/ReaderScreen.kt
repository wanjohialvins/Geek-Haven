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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.BrightnessMedium


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

    // Read Mode State (0 = Light, 1 = Sepia, 2 = Dark)
    var readMode by remember { mutableStateOf(0) }
    
    // Auto Flip State
    var autoFlipEnabled by remember { mutableStateOf(false) }
    var autoFlipInterval by remember { mutableStateOf(10L) } // Seconds
    
    val colorFilter = when (readMode) {
        1 -> androidx.compose.ui.graphics.ColorFilter.tint(Color(0x335D4037), androidx.compose.ui.graphics.BlendMode.Darken) // Sepia-ish overlay
        2 -> androidx.compose.ui.graphics.ColorFilter.colorMatrix(androidx.compose.ui.graphics.ColorMatrix().apply { setToSaturation(0f); setToScale(-1f, -1f, -1f, 1f) }) // Inverted/Dark
        else -> null
    }
    
    val bgColor = when (readMode) {
        1 -> Color(0xFFF4ECD8) // Sepia Paper
        2 -> Color.Black
        else -> Color.White
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
            
            // Auto Flip Logic
            LaunchedEffect(autoFlipEnabled, autoFlipInterval) {
                if (autoFlipEnabled) {
                    while (true) {
                        kotlinx.coroutines.delay(autoFlipInterval * 1000L)
                        if (pagerState.currentPage < s.pageCount - 1) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        } else {
                            autoFlipEnabled = false // Stop at end
                        }
                    }
                }
            }
            
            // Track page changes
            LaunchedEffect(pagerState.currentPage) {
                viewModel.saveProgress(pagerState.currentPage)
            }
            
            Box(modifier = Modifier.fillMaxSize()) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize().background(bgColor)
                ) { pageIndex ->
                    PdfPage(index = pageIndex, viewModel = viewModel, colorFilter = colorFilter)
                }
                
                // Overlay Controls (Top Center for Read Mode, Bottom for Auto Flip)
                // In immersive mode we might hide these, but for "Read Mode" as a filter, let's keep simple buttons.
                
                // Theme Toggle
                androidx.compose.material3.FloatingActionButton(
                    onClick = { readMode = (readMode + 1) % 3 },
                    modifier = Modifier.align(Alignment.TopEnd).padding(16.dp).size(48.dp),
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha=0.7f)
                ) {
                    Icon(
                        androidx.compose.material.icons.Icons.Default.BrightnessMedium, 
                        contentDescription = "Theme",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                
                // Auto Flip Toggle
                Row(
                    modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (autoFlipEnabled) {
                        Text("${autoFlipInterval}s", style = MaterialTheme.typography.labelSmall, modifier=Modifier.background(MaterialTheme.colorScheme.surface.copy(alpha=0.7f)).padding(4.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    
                    androidx.compose.material3.FloatingActionButton(
                        onClick = { autoFlipEnabled = !autoFlipEnabled },
                        modifier = Modifier.size(48.dp),
                        containerColor = if(autoFlipEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface.copy(alpha=0.7f)
                    ) {
                         Icon(
                            androidx.compose.material.icons.Icons.Default.PlayArrow, // Or a generic 'Auto' icon
                            contentDescription = "Auto Flip",
                            tint = if(autoFlipEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
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

    // Lock Mode State
    var isLocked by remember { mutableStateOf(false) }
    
    // Sleep Timer Dialog State
    var showTimerDialog by remember { mutableStateOf(false) }

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
                    // Lock Button (Always visible)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                         androidx.compose.material3.IconButton(onClick = { isLocked = !isLocked }) {
                            Icon(
                                imageVector = if (isLocked) androidx.compose.material.icons.Icons.Default.Lock else androidx.compose.material.icons.Icons.Default.LockOpen,
                                contentDescription = "Lock Controls",
                                tint = if (isLocked) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                            )
                         }
                    }

                    // Content - Disabled or Hidden instructions if Locked? 
                    // Usually we just disable interactions. 
                    // We can use 'enabled' params or an overlay. Simple boolean check is enough.
                    
                    Text(text = "Now Playing", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Headphones,
                        contentDescription = null,
                        modifier = Modifier.size(120.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Controls (Disabled when locked)
                    val controlsEnabled = !isLocked
                    
                    // Progress Slider
                    androidx.compose.material3.Slider(
                        value = s.position.toFloat(),
                        onValueChange = { if (controlsEnabled) viewModel.seekTo(it.toLong()) },
                        valueRange = 0f..s.duration.toFloat().coerceAtLeast(1f),
                        enabled = controlsEnabled
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
                         androidx.compose.material3.IconButton(
                             onClick = { viewModel.seekTo((s.position - 10000).coerceAtLeast(0)) },
                             enabled = controlsEnabled
                         ) {
                            Icon(androidx.compose.material.icons.Icons.Default.Replay10, "Rewind 10s")
                         }
                         
                         Spacer(modifier = Modifier.width(16.dp))
                    
                        Button(
                            onClick = { viewModel.togglePlayPause() },
                            modifier = Modifier.size(64.dp),
                            shape = androidx.compose.foundation.shape.CircleShape,
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
                            enabled = controlsEnabled
                        ) {
                            Icon(
                                imageVector = if (viewModel.player?.isPlaying == true) androidx.compose.material.icons.Icons.Default.Pause else androidx.compose.material.icons.Icons.Default.PlayArrow,
                                contentDescription = "Play/Pause"
                            )
                        }
                        
                         Spacer(modifier = Modifier.width(16.dp))
                         
                         androidx.compose.material3.IconButton(
                             onClick = { viewModel.seekTo((s.position + 30000).coerceAtMost(s.duration)) },
                             enabled = controlsEnabled
                         ) {
                            Icon(androidx.compose.material.icons.Icons.Default.Forward30, "Forward 30s")
                         }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Row {
                         // Speed Toggle
                        var speed by remember { mutableStateOf(1.0f) }
                        TextButton(
                            onClick = {
                                speed = if (speed >= 2.0f) 0.75f else speed + 0.25f
                                viewModel.setPlaybackSpeed(speed)
                            },
                            enabled = controlsEnabled
                        ) {
                            Text("Speed: ${speed}x")
                        }
                        
                        // Sleep Timer
                        TextButton(
                            onClick = { showTimerDialog = true },
                            enabled = controlsEnabled
                        ) {
                            Text("Sleep Timer")
                        }
                    }
                }
            }
        }
        
        if (showTimerDialog) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showTimerDialog = false },
                title = { Text("Set Sleep Timer") },
                text = {
                     Column {
                         listOf(15, 30, 45, 60).forEach { mins ->
                             TextButton(onClick = { 
                                 viewModel.setSleepTimer(mins)
                                 showTimerDialog = false 
                             }) {
                                 Text("$mins minutes")
                             }
                         }
                         TextButton(onClick = { 
                             viewModel.setSleepTimer(0) // Cancel
                             showTimerDialog = false
                         }) {
                             Text("Off", color = MaterialTheme.colorScheme.error)
                         }
                     }
                },
                confirmButton = {}
            )
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
