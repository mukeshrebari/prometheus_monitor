package com.example.prommonitor.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColors = darkColorScheme()
private val LightColors = lightColorScheme()

@Composable
fun AppTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColors else LightColors
    val systemUiController = rememberSystemUiController()
    SideEffect { systemUiController.setSystemBarsColor(colors.background) }
    MaterialTheme(colorScheme = colors, content = content)
}
