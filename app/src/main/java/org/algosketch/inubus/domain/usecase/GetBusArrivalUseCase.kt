package org.algosketch.inubus.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.common.mapper.BusArrivalInfoMapper
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository

class GetBusArrivalUseCase(private val infoRepository: BusArrivalInfoRepository) {
    suspend operator fun invoke(busNumber: String, busStop: String): BusArrivalInfo {
        val busArrivals = when(busStop) {
            "인천대입구" -> fetchINU()
            "지식정보단지" -> fetchBIT()
            "정문" -> fetchGate()
            "공과대" -> fetchCOE()
            else -> listOf()
        }

        return busArrivals.find { busArrival ->
            busArrival.busNumber == busNumber
        } ?: throw java.lang.RuntimeException("버스 정보 없음")
    }

    private suspend fun fetchINU() : List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            val list1 = request("164000395")
            val list2 = request("164000396")
            list1 + list2
        }
    }

    private suspend fun fetchBIT() : List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            val list1 = request("164000403")
            val list2 = request("164000380")
            list1 + list2
        }
    }

    private suspend fun fetchGate(): List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            request("164000385")
        }
    }

    private suspend fun fetchCOE(): List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            request("164000387")
        }
    }

    private suspend fun request(bstop: String): List<BusArrivalInfo> {
        return withContext(Dispatchers.IO) {
            BusArrivalInfoMapper.toBusArrival(infoRepository.getBusArrival(bstop))
        }
    }
}