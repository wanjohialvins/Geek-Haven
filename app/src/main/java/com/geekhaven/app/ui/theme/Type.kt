package com.geekhaven.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Ideally, we would add fonts to res/font and load them here.
// For now, we will use default Serif for "Reading" and SansSerif for "UI".

val ReadingFontFamily = FontFamily.Serif
val UIFontFamily = FontFamily.SansSerif

val Typography = Typography(
    // Large Headlines (UI Layer)
    headlineLarge = TextStyle(
        fontFamily = UIFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = UIFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    // Titles (UI Layer)
    titleLarge = TextStyle(
        fontFamily = UIFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // Body Text (Reading Layer - Primary content)
    bodyLarge = TextStyle(
        fontFamily = ReadingFontFamily, // Serif for reading
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp, // Slightly larger for comfort
        lineHeight = 28.sp, // Relaxed line height
        letterSpacing = 0.5.sp
    ),
    // UI Label text
    bodyMedium = TextStyle(
        fontFamily = UIFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelSmall = TextStyle(
        fontFamily = UIFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
