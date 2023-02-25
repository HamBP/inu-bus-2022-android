package org.algosketch.inubus.presentation.ui.extension

import androidx.compose.ui.graphics.Color
import org.algosketch.inubus.presentation.ui.theme.*

fun Int.toRestTimeFormat(): String {
    if (this < 60) return "곧도착"

    return "${this / 60}분 ${this % 60}초"
}

fun String.color(): Color {
    return when (this) {
        "red" -> busRed
        "blue" -> busBlue
        "purple" -> busPurple
        "green" -> busGreen
        else -> primary
    }
}