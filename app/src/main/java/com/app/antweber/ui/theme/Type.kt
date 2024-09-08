package com.app.antweber.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.antweber.R


val robotoFontFamily = FontFamily(
    Font(R.font.roboto_regular)
)

val myCustomFont = FontFamily(
    Font(R.font.roboto_regular)
)

val FontForSplash = FontFamily(
    Font(R.font.roboto_medium)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.6.sp
    )
)