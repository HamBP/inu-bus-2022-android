package org.algosketch.inubus.global.util

import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.data.model.BusInformation

object BusInformationUtil {
    fun transferBusData(busArrival: BusArrival) : List<BusInformation> {
        val itemList = busArrival.msgBody?.itemList ?: return arrayListOf()

        val result = itemList
            .filter { Bus.getRouteIdsByBusStop(itemList[0].BSTOPID).contains(it.ROUTEID) }
            .map {
                val busNumber = Bus.busNumbers[it.ROUTEID] ?: "?"
                BusInformation(
                    restTime = (it.ARRIVALESTIMATETIME / 60),
                    busNumber = busNumber,
                    busColor = Bus.getBusColorByBusNumber(busNumber),
                    exit = Bus.getExit(it.BSTOPID)
            ) }

        return result
    }
}