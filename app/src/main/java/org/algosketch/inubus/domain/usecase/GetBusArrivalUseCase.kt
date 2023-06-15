package org.algosketch.inubus.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.data.repository.TargetBusListRepository
import org.algosketch.inubus.di.BusArrivalInfoRemoteRepository
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository
import org.algosketch.inubus.domain.repository.BusStop
import javax.inject.Inject

class GetBusArrivalUseCase @Inject constructor(
    @BusArrivalInfoRemoteRepository private val infoRepository: BusArrivalInfoRepository,
    private val targetBusListRepository: TargetBusListRepository,
) {
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
        } ?: BusArrivalInfo()
    }

    private suspend fun fetchINU() : List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            val list1 = request(BusStop.INU_STATION_EXIT_1)
            val list2 = request(BusStop.INU_STATION_EXIT_2)
            list1 + list2
        }
    }

    private suspend fun fetchBIT() : List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            val list1 = request(BusStop.BIT_3)
            val list2 = request(BusStop.BIT_4)
            list1 + list2
        }
    }

    private suspend fun fetchGate(): List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            request(BusStop.MAIN_GATE_OF_INU)
        }
    }

    private suspend fun fetchCOE(): List<BusArrivalInfo> {
        return withContext(Dispatchers.Default) {
            request(BusStop.COLLEGE_OF_ENGINEERING)
        }
    }

    private suspend fun request(busStop: BusStop): List<BusArrivalInfo> {
        val targetBusList = targetBusListRepository.getBusNumbers(busStop)
        return withContext(Dispatchers.IO) {
            infoRepository.getBusArrival(busStop).toBusArrivals().filter {
                targetBusList.contains(it.busNumber)
            }
        }
    }
}