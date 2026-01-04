package com.geekhaven.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "books")
@TypeConverters(Converters::class)
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    
    // Core Metadata (Google Books / User)
    val googleBookId: String? = null,
    val title: String,
    val authors: List<String> = emptyList(), // JSON Converted
    val description: String? = null,
    val isbn: String? = null,
    val coverUrl: String? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    
    // Series Metadata
    val isSeries: Boolean = false,
    val seriesName: String? = null,
    val seriesIndex: Double? = null, // Double to support 1.5 etc
    
    // User Data
    val notes: String? = null,
    val isFavorite: Boolean = false,
    val addedDate: Long = System.currentTimeMillis(),
    
    // Unified Progress
    val readingStatus: ReadingStatus = ReadingStatus.NOT_STARTED,
    val progressPercentage: Int = 0, // 0-100
    val lastReadTime: Long = 0L,
    
    // Exact Progress
    val currentPage: Int = 0, // For PDF/EPUB
    val audioPosition: Long = 0L, // For Audio (ms)
    
    // Intelligence & Memory
    val memoryAnchor: String? = null, // "If You Stop Now" context note
    
    // Available Formats & URIs
    val pdfUri: String? = null,
    val epubUri: String? = null,
    val audioUri: String? = null,
    
    val hasPdf: Boolean = false,
    val hasEpub: Boolean = false,
    val hasAudio: Boolean = false,
    val hasPhysical: Boolean = false
)

enum class ReadingStatus {
    NOT_STARTED,
    READING,
    COMPLETED,
    DNF // Did Not Finish
}

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromReadingStatus(value: ReadingStatus): String {
        return value.name
    }

    @TypeConverter
    fun toReadingStatus(value: String): ReadingStatus {
        return try {
            ReadingStatus.valueOf(value)
        } catch (e: Exception) {
            ReadingStatus.NOT_STARTED
        }
    }
}
