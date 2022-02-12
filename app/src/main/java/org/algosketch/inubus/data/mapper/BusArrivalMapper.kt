package org.algosketch.inubus.data.mapper

import androidx.lifecycle.MutableLiveData
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.global.util.Bus

object BusArrivalMapper {
    fun toBusArrival(busArrivalResponse: BusArrivalResponse) : List<org.algosketch.inubus.domain.entity.BusArrival> {
        val itemList = busArrivalResponse.msgBody?.itemList ?: return arrayListOf()

        val result = itemList
            .filter { Bus.getRouteIdsByBusStop(itemList[0].BSTOPID).contains(it.ROUTEID) }
            .map {
                val busNumber = Bus.busNumbers[it.ROUTEID] ?: "?"
                val restTime = it.ARRIVALESTIMATETIME / 60
                val where = Bus.getBusStopName(it.BSTOPID)
                val exit = Bus.getExit(it.BSTOPID)
                org.algosketch.inubus.domain.entity.BusArrival(
                    restTime = restTime,
                    busNumber = busNumber,
                    busColor = Bus.getBusColorByBusNumber(busNumber),
                    exit = Bus.getExit(it.BSTOPID),
                    where = where,
                    restTimeInformationText = MutableLiveData("${where}역 ${exit}번 출구"),
                    exitInformationText = MutableLiveData("버스가 ${restTime}분 뒤 도착해요.")
                )
            }

        return result
    }
}