package org.algosketch.inubus.data.repository

import org.algosketch.inubus.domain.repository.BusStop

fun BusStop.toBusId() = busStopToBusStopIdMap[this]!!

private val busStopToBusStopIdMap: Map<BusStop, String> = mapOf(
    BusStop.INU_STATION_EXIT_1 to "164000396",
    BusStop.INU_STATION_EXIT_2 to "164000395",
    BusStop.BIT_3 to "164000403",
    BusStop.BIT_4 to "164000380",
    BusStop.MAIN_GATE_OF_INU to "164000385",
    BusStop.COLLEGE_OF_ENGINEERING to "164000377",
)