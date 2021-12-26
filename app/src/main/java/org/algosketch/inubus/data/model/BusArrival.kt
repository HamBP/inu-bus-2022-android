package org.algosketch.inubus.data.model

data class BusArrival(
    val busNumber: String,
    val restTime: Int,
    val estimatedTime: Int = 0)