package com.mehdizadeh.catfeed.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.mehdizadeh.catfeed.domain.repository.ThemeMode


object AppTheme {
    val colorScheme: CustomColors
        @ReadOnlyComposable
        @Composable
        get() = LocalColors.current

}

val LocalColors = staticCompositionLocalOf { lightColors() }

@Composable
fun CatTheme(
    // Dynamic color is available on Android 12+
    themeMode: ThemeMode = ThemeMode.LIGHT,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }


    val currentColor = if (darkTheme) darkColors() else lightColors()
    val rememberedColors = remember(currentColor) { currentColor.copy() }.apply {
        updateColorsFrom(currentColor)
    }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
    ) {
        ProvideTextStyle(typography.bodyMedium, content = content)
    }
}