package com.hungduy.pharmacycall.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun PharmacyCallTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = Primary,
        onPrimary = OnPrimary,
        secondary = SecondaryBlue,
        background = Background,
        surface = Surface,
        error = Error,
        onSurfaceVariant = OnSurfaceVariant
    )
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
