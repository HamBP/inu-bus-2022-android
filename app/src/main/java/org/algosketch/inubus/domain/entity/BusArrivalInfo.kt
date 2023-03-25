package org.algosketch.inubus.domain.entity

data class BusArrivalInfo(
    val restTime: Int = 0,
    val busNumber: String = "0",
    val busColor: String = "blue",
    val exit: Int = 0,
    val where: String = "UNKNONW",
    val lastStop: String = "",
) {
    fun getBusStops(direction: Int): List<String> {
        return if(direction == TO_SCHOOL) BusStops.toSchool[busNumber] ?: listOf()
        else BusStops.toSchool[busNumber] ?: listOf()
    }

    companion object {
        const val TO_SCHOOL = 0
        const val TO_HOME = 1

        val toHomeBusStops = HashMap<String, List<String>>()
    }
}
