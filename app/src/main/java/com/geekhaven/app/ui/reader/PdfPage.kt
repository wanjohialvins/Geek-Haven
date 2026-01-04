package com.geekhaven.app.ui.reader

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun PdfPage(
    index: Int,
    viewModel: PdfViewModel,
    colorFilter: androidx.compose.ui.graphics.ColorFilter? = null
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(index) {
        bitmap = viewModel.renderPage(index)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        bitmap?.let { btm ->
            // Use fully qualified if not imported, or import it.
            com.geekhaven.app.ui.components.ZoomableBox {
                 Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = "Page ${index + 1}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit, // Fit page within screen
                    colorFilter = colorFilter
                )
            }
        } ?: run {
            CircularProgressIndicator()
        }
    }
}
