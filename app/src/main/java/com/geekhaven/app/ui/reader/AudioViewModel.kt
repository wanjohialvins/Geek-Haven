package com.geekhaven.app.ui.reader

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bookRepository: com.geekhaven.app.domain.repository.BookRepository
) : ViewModel() {

    private var _player: ExoPlayer? = null
    val player: ExoPlayer? get() = _player

    private val _state = MutableStateFlow<AudioPlayerState>(AudioPlayerState.Loading)
    val state: StateFlow<AudioPlayerState> = _state.asStateFlow()
    
    // Progress polling
    init {
        viewModelScope.launch {
            while (isActive) {
                _player?.let {
                    if (it.isPlaying) {
                         _state.value = AudioPlayerState.Playing(
                             position = it.currentPosition,
                             duration = it.duration.coerceAtLeast(0)
                         )
                         // Optionally auto-save periodically here
                    }
                }
                delay(1000)
            }
        }
    }

    private var currentBookId: Long? = null

    fun loadAudio(bookId: Long) {
        currentBookId = bookId
        viewModelScope.launch {
            try {
                val book = bookRepository.getBookById(bookId)
                val uriStr = book?.audioUri
                if (uriStr == null) {
                    _state.value = AudioPlayerState.Error("Audio URI not found")
                    return@launch
                }

                initPlayer(Uri.parse(uriStr))
            } catch (e: Exception) {
                _state.value = AudioPlayerState.Error(e.message ?: "Error loading audio")
            }
        }
    }

    private fun initPlayer(uri: Uri) {
        if (_player == null) {
            _player = ExoPlayer.Builder(context).build()
        }
        _player?.apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true
        }
    }

    fun togglePlayPause() {
        _player?.let {
            if (it.isPlaying) {
                it.pause()
                saveProgress()
            } else {
                it.play()
            }
        }
    }
    
    private fun saveProgress() {
        // Simple internal save
        val p = _player ?: return
        val currentBookId = currentBookId ?: return
        
        viewModelScope.launch {
            bookRepository.updateProgress(
                bookId = currentBookId,
                page = 0,
                audioPos = p.currentPosition,
                percentage = if (p.duration > 0) ((p.currentPosition.toFloat() / p.duration) * 100).toInt() else 0,
                status = com.geekhaven.app.data.local.entity.ReadingStatus.READING
            )
        }
    }

    fun saveMemoryAnchor(anchor: String) {
        val bookId = currentBookId ?: return
        viewModelScope.launch {
            bookRepository.updateMemoryAnchor(bookId, anchor)
        }
    }

    override fun onCleared() {
        super.onCleared()
        saveProgress() // Save on exit
        _player?.release()
        _player = null
    }
}

sealed class AudioPlayerState {
    object Loading : AudioPlayerState()
    data class Playing(val position: Long, val duration: Long) : AudioPlayerState()
    data class Error(val message: String) : AudioPlayerState()
    // Paused state can be inferred or explicit, for MVP 'Playing' just carries data
}
