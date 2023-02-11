package org.algosketch.inubus.common.mapper

import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.common.util.Bus

object BusArrivalInfoMapper {
    fun toBusArrival(busArrivalResponse: BusArrivalResponse) : List<BusArrivalInfo> {
        val itemList = busArrivalResponse.msgBody?.itemList ?: return arrayListOf()

        val result = itemList
            .filter { Bus.getRouteIdsByBusStop(itemList[0].BSTOPID).contains(it.ROUTEID) }
            .map {
                val busNumber = Bus.busNumbers[it.ROUTEID] ?: "?"
                val restTime = it.ARRIVALESTIMATETIME / 60
                val where = Bus.getBusStopName(it.BSTOPID)
                val exit = Bus.getExit(it.BSTOPID)
                BusArrivalInfo(
                    restTime = it.ARRIVALESTIMATETIME,
                    busNumber = busNumber,
                    busColor = Bus.getBusColorByBusNumber(busNumber),
                    exit = Bus.getExit(it.BSTOPID),
                    where = where,
                    restTimeInformationText = "${where}역 ${exit}번 출구",
                    exitInformationText = "버스가 ${restTime}분 뒤 도착해요."
                )
            }

        return result
    }
}