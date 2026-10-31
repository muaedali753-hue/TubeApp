package com.example.tubeapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Red = Color(0xFFFF0000)

private val Light = lightColorScheme(primary = Red)
private val Dark = darkColorScheme(
    primary = Red,
    background = Color(0xFF0F0F0F),
    surface = Color(0xFF0F0F0F)
)

@Composable
fun TubeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) Dark else Light,
        content = content
    )
}
