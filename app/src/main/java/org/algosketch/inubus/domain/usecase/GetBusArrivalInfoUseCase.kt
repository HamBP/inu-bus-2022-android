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
        return withContext(Dispatchers.Default) {
            when(busStop) {
                "인천대입구" -> fetchINU()
                "지식정보단지" -> fetchBIT()
                else -> listOf()
            }
        }
    }

    private suspend fun fetchINU() = coroutineScope {
        withContext(Dispatchers.Default) {
            val list1 = BusArrivalInfoMapper.toBusArrival(request("164000395"))
            val list2 = BusArrivalInfoMapper.toBusArrival(request("164000396"))
            list1 + list2
        }
    }

    private suspend fun fetchBIT() = coroutineScope {
        withContext(Dispatchers.Default) {
            val list1 = BusArrivalInfoMapper.toBusArrival(request("164000403"))
            val list2 = BusArrivalInfoMapper.toBusArrival(request("164000380"))
            list1 + list2
        }
    }

    private suspend fun request(bstop: String): BusArrivalResponse = withContext(Dispatchers.IO) {
        infoRepository.getArrivalBusTime(bstop)
    }
}