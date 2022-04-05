package org.algosketch.inubus.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.algosketch.inubus.data.mapper.BusArrivalInfoMapper
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository

class GetBusArrivalInfoUseCase(private val infoRepository: BusArrivalInfoRepository) {
    suspend operator fun invoke(busStop: String): List<BusArrivalInfo> {
        return when(busStop) {
            "인천대입구" -> fetchINU()
            "지식정보단지" -> fetchBIT()
            else -> listOf()
        }
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

    private suspend fun request(bstop: String): List<BusArrivalInfo> {
        return withContext(Dispatchers.IO) {
            BusArrivalInfoMapper.toBusArrival(infoRepository.getArrivalBusTime(bstop))
        }
    }

}