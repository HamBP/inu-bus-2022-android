package org.algosketch.inubus.global.util

import android.util.Log
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.data.model.BusInformation
import org.algosketch.inubus.global.constant.Bus

object BusInformationUtil {
    fun transferBusData(busArrival: BusArrival) : List<BusInformation> {
        val itemList = busArrival.msgBody!!.itemList!!

        val result = itemList
            .filter { Bus.getRouteIdsByBusStop(itemList[0].BSTOPID).contains(it.ROUTEID) }
            .map {
                val busNumber = Bus.busNumbers[it.ROUTEID] ?: "?"
                BusInformation(
                    restTime = (it.ARRIVALESTIMATETIME / 60),
                    busNumber = busNumber,
                    busColor = Bus.getBusColorByBusNumber(busNumber)
            ) }

        return result
    }
}