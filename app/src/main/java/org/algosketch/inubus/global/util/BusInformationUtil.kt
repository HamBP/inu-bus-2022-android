package org.algosketch.inubus.global.util

import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.data.model.BusInformation

object BusInformationUtil {
    fun transferBusData(busArrival: BusArrival) : List<BusInformation> {
        val itemList = busArrival.msgBody!!.itemList!!

        val result = itemList
            .filter { it.ROUTEID ==  164000395 }
            .map { BusInformation(
                restTime = (it.ARRIVALESTIMATETIME / 60) as Int,
                busNumber = "8"
            ) }

        return result
    }
}