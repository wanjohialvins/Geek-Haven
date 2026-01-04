package com.geekhaven.app.domain.model

data class LocalFile(
    val uri: String,
    val name: String,
    val type: FileType,
    val size: Long,
    val mimeType: String?,
    // Extracted metadata (optional)
    val title: String? = null,
    val author: String? = null
)

enum class FileType {
    PDF, EPUB, AUDIO, UNKNOWN
}
