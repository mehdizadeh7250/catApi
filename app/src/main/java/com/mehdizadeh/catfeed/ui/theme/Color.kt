package com.mehdizadeh.catfeed.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class CustomColors(
    primary: Color,
    onPrimary: Color,
) {
    var primary by mutableStateOf(primary)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set


    fun copy(
        primary: Color = this.primary,
        onPrimary: Color = this.onPrimary,
    ) = CustomColors(
        primary = primary,
        onPrimary = onPrimary,
    )

    // Will be explain later
    fun updateColorsFrom(other: CustomColors) {
        primary = other.primary
        onPrimary = other.onPrimary
    }
}

fun lightColors() = CustomColors(
    primary = Color.Black,
    onPrimary = Color.White,
)

fun darkColors() = CustomColors(
    primary = Color.White,
    onPrimary = Color.Black,
)