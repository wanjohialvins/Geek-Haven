package com.geekhaven.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// "Nature Influence" - Rounded corners, soft containers
val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp), // Minimal rounding
    small = RoundedCornerShape(8.dp),      // Standard UI elements
    medium = RoundedCornerShape(16.dp),    // Cards, "Leaf" like shapes
    large = RoundedCornerShape(24.dp),     // Bottom sheets, large modals
    extraLarge = RoundedCornerShape(32.dp) // Large featured items
)
