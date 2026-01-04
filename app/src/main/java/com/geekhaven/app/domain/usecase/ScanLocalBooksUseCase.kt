package com.geekhaven.app.domain.usecase

import android.net.Uri
import com.geekhaven.app.data.local.entity.BookEntity
import com.geekhaven.app.data.local.source.LocalFileDataSource

import com.geekhaven.app.domain.model.LocalFile
import com.geekhaven.app.domain.repository.BookRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ScanLocalBooksUseCase @Inject constructor(
    private val localFileDataSource: LocalFileDataSource,
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(treeUri: Uri) {
        // 1. Scan files
        val files = localFileDataSource.scanDirectory(treeUri)
        
        // 2. Group by normalized name (Simple Heuristic for now)
        val groupedFiles = files.groupBy { normalizeName(it.name) }
        
        // 3. Process each group
        groupedFiles.forEach { (baseName, fileList) ->
            // existing matching logic could go here (check DB for title match)
            
            // For now, create/update based on file
            val pdfFile = fileList.find { it.type == com.geekhaven.app.domain.model.FileType.PDF }
            val epubFile = fileList.find { it.type == com.geekhaven.app.domain.model.FileType.EPUB }
            val audioFile = fileList.find { it.type == com.geekhaven.app.domain.model.FileType.AUDIO }
            
            // Basic Entity construction
            val book = BookEntity(
                title = baseName, // Temporary title
                // Populate URIs
                pdfUri = pdfFile?.uri,
                epubUri = epubFile?.uri,
                audioUri = audioFile?.uri,
                // Flags
                hasPdf = pdfFile != null,
                hasEpub = epubFile != null,
                hasAudio = audioFile != null,
                // Default cover placeholder if not fetched
                coverUrl = null 
            )
            
            // Insert (In real app, we would UPSERT or match existing)
            // Ideally check if book with this title exists
            // But strict unique constraints aren't set on title. 
            // We'll just insert for now. 
             bookRepository.insertBook(book)
        }
    }

    private fun normalizeName(filename: String): String {
        return filename.substringBeforeLast(".")
            .replace("_", " ")
            .replace("-", " ")
            .replace(Regex("\\s+"), " ") // collapse spaces
            .trim()
    }
}
