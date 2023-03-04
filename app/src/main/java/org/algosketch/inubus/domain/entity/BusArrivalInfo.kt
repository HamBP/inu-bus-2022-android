package org.algosketch.inubus.domain.entity

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import org.algosketch.inubus.common.util.Bus

data class BusArrivalInfo(
    val restTime: Int = 0,
    val busNumber: String = "0",
    val busColor: String = "blue",
    val exit: Int = 0,
    val where: String = "인천대입구",
    val restTimeInformationText: String = "",
    val exitInformationText: String = "",
    val navigateDetail: (View) -> Unit = {},
    val lastStop: String = "",
) {
    fun toBundle(): Bundle {
        return bundleOf(
            "exit" to exit,
            "where" to where,
            "busNumber" to busNumber,
            "distance" to Bus.getDistance(where, busNumber),
            "restTime" to restTime,
            "busColor" to Bus.getBusColorByBusNumber(busNumber)
        )
    }

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
