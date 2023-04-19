package org.algosketch.inubus.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.common.mapper.BusArrivalInfoMapper
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBusArrivalsUseCase @Inject constructor(
    private val infoRepository: BusArrivalInfoRepository,
) {
    suspend operator fun invoke(busStop: String): List<BusArrivalInfo> {
        return when(busStop) {
            "인천대입구" -> fetchINU()
            "지식정보단지" -> fetchBIT()
            "정문" -> fetchGate()
            "공과대" -> fetchCOE()
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