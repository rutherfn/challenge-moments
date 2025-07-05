package com.nicholas.rutherford.moments.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Custom typography for the Moments app
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,      // Default system font
        fontWeight = FontWeight.Normal,       // Normal weight for text
        fontSize = 16.sp,                     // Font size for body text
        lineHeight = 24.sp,                   // Line height for readability
        letterSpacing = 0.5.sp                // Small letter spacing
    )
)
