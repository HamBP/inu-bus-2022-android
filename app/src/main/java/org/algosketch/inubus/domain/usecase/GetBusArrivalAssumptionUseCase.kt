package org.algosketch.inubus.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.common.mapper.BusArrivalInfoMapper
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository

class GetBusArrivalAssumptionUseCase(private val infoRepository: BusArrivalInfoRepository) {
    suspend operator fun invoke(busNumber: String): BusArrivalInfo {
        val busArrivals = getText()

        println("제발제발 $busArrivals")

        return busArrivals.find { busArrival ->
            busArrival.busNumber == busNumber
        } ?: throw java.lang.RuntimeException("버스 정보 없음")
    }

    private suspend fun getText() : List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            request("164000055") // 8번 버스 테스트
        }
    }

    private suspend fun request(bstop: String): List<BusArrivalInfo> {
        return withContext(Dispatchers.IO) {
            infoRepository.getBusArrival(bstop).toBusArrivals()
        }
    }
}