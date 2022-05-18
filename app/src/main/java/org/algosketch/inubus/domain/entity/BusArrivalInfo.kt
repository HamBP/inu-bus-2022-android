package org.algosketch.inubus.domain.entity

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import org.algosketch.inubus.common.util.Bus

data class BusArrivalInfo(
    val restTime: Int,
    val busNumber: String,
    val busColor: String,
    val exit: Int,
    val where: String,
    val restTimeInformationText: String,
    val exitInformationText: String,
    val navigateDetail: (View) -> Unit = {}
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
}