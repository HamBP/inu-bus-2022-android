package org.algosketch.inubus.global.util

import org.algosketch.inubus.data.model.BusArrivalResponse

object BusInformationUtil {
    fun transferBusData(busArrivalResponse: BusArrivalResponse) : List<org.algosketch.inubus.domain.entity.BusArrival> {
        val itemList = busArrivalResponse.msgBody?.itemList ?: return arrayListOf()

        val result = itemList
            .filter { Bus.getRouteIdsByBusStop(itemList[0].BSTOPID).contains(it.ROUTEID) }
            .map {
                val busNumber = Bus.busNumbers[it.ROUTEID] ?: "?"
                org.algosketch.inubus.domain.entity.BusArrival(
                    restTime = (it.ARRIVALESTIMATETIME / 60),
                    busNumber = busNumber,
                    busColor = Bus.getBusColorByBusNumber(busNumber),
                    exit = Bus.getExit(it.BSTOPID)
                )
            }

        return result
    }
}