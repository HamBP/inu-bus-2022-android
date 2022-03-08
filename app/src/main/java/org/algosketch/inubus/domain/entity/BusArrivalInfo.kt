package org.algosketch.inubus.domain.entity

data class BusArrivalInfo(
    val restTime: Int,
    val busNumber: String,
    val busColor: String,
    val exit: Int,
    val where: String,
    val restTimeInformationText: String,
    val exitInformationText: String
)