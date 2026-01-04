package com.geekhaven.app.ui.reader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bookRepository: com.geekhaven.app.domain.repository.BookRepository // To get URI
) : ViewModel() {

    private var fileDescriptor: ParcelFileDescriptor? = null
    private var pdfRenderer: PdfRenderer? = null
    private val rendererMutex = Mutex()

    private val _state = MutableStateFlow<PdfReaderState>(PdfReaderState.Loading)
    val state: StateFlow<PdfReaderState> = _state.asStateFlow()
    
    private var currentBookId: Long? = null

    fun loadPdf(bookId: Long) {
        currentBookId = bookId
        viewModelScope.launch {
            try {
                _state.value = PdfReaderState.Loading
                val book = bookRepository.getBookById(bookId)
                val uriStr = book?.pdfUri
                if (uriStr == null) {
                    _state.value = PdfReaderState.Error("PDF URI not found for book")
                    return@launch
                }
                
                // Init with saved page
                val initialPage = book?.currentPage ?: 0
                initRenderer(Uri.parse(uriStr), initialPage)
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = PdfReaderState.Error(e.message ?: "Unknown error loading PDF")
            }
        }
    }

    private suspend fun initRenderer(uri: Uri, initialPage: Int) = withContext(Dispatchers.IO) {
        try {
            fileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")
            fileDescriptor?.let {
                pdfRenderer = PdfRenderer(it)
                _state.value = PdfReaderState.Ready(pageCount = pdfRenderer?.pageCount ?: 0, initialPage = initialPage)
            }
        } catch (e: Exception) {
            _state.value = PdfReaderState.Error("Failed to open PDF: ${e.message}")
        }
    }

    suspend fun renderPage(index: Int): Bitmap? = withContext(Dispatchers.Default) {
        rendererMutex.withLock {
            val renderer = pdfRenderer ?: return@withContext null
            if (index < 0 || index >= renderer.pageCount) return@withContext null

            try {
                val page = renderer.openPage(index)
                // High res bitmap (e.g., screen width * density)
                // For simplicity, using a fixed reasonably high width, relying on scaling
                val width = 1080 
                val height = (width.toFloat() / page.width * page.height).toInt()
                
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                page.close()
                return@withContext bitmap
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    fun saveProgress(pageIndex: Int) {
        val bookId = currentBookId ?: return
        val currentCount = (state.value as? PdfReaderState.Ready)?.pageCount ?: 1
        
        viewModelScope.launch {
            bookRepository.updateProgress(
                bookId = bookId,
                page = pageIndex,
                audioPos = 0, // Preserve? Need to fetch book again or ignore. Ideally fetch. For now 0.
                percentage = ((pageIndex.toFloat() / currentCount) * 100).toInt(),
                status = com.geekhaven.app.data.local.entity.ReadingStatus.READING
            )
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        try {
            pdfRenderer?.close()
            fileDescriptor?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

sealed class PdfReaderState {
    object Loading : PdfReaderState()
    data class Ready(val pageCount: Int, val initialPage: Int) : PdfReaderState()
    data class Error(val message: String) : PdfReaderState()
}
