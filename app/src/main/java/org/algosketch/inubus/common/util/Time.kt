package org.algosketch.inubus.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Time {
    val currentTime: String get() = getCurrentFormattedTime()

    private fun getCurrentFormattedTime(): String {
        val dateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        return dateTime.format(formatter)
    }
}