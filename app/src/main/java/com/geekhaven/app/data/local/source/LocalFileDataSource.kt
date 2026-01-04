package com.geekhaven.app.data.local.source

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import com.geekhaven.app.domain.model.FileType
import com.geekhaven.app.domain.model.LocalFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalFileDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun scanDirectory(treeUri: Uri): List<LocalFile> = withContext(Dispatchers.IO) {
        val root = DocumentFile.fromTreeUri(context, treeUri) ?: return@withContext emptyList()
        val results = mutableListOf<LocalFile>()
        traverse(root, results)
        results
    }

    private fun traverse(dir: DocumentFile, results: MutableList<LocalFile>) {
        val files = dir.listFiles()
        for (file in files) {
            if (file.isDirectory) {
                traverse(file, results)
            } else if (file.isFile) {
                val type = determineFileType(file.type, file.name)
                if (type != FileType.UNKNOWN) {
                    results.add(
                        LocalFile(
                            uri = file.uri.toString(),
                            name = file.name ?: "Unknown",
                            type = type,
                            size = file.length(),
                            mimeType = file.type
                        )
                    )
                }
            }
        }
    }

    private fun determineFileType(mimeType: String?, name: String?): FileType {
        val lowerName = name?.lowercase() ?: ""
        return when {
            mimeType == "application/pdf" || lowerName.endsWith(".pdf") -> FileType.PDF
            mimeType == "application/epub+zip" || lowerName.endsWith(".epub") -> FileType.EPUB
            mimeType?.startsWith("audio/") == true || lowerName.endsWith(".mp3") || lowerName.endsWith(".m4b") -> FileType.AUDIO
            else -> FileType.UNKNOWN
        }
    }
}
