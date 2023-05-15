package org.algosketch.inubus.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.domain.repository.BusStop
import javax.inject.Inject
import javax.inject.Singleton

/**
 * TODO : 추후 Firebase에서 데이터를 가져와야 함.
 * @param 출발지
 * @return 출력 대상이 되는 버스 목록을 버스 번호(String)로 반환
 */
@Singleton
class TargetBusListRepository @Inject constructor() {

    suspend fun getBusNumbers(targetBusStop: BusStop): List<String> {
        return withContext(Dispatchers.IO) {
            val busStopId = targetBusStop.toBusId()
            getRouteIdsByBusStop(busStopId)
        }
    }

    private fun getRouteIdsByBusStop(busStop: String) : List<String> {
        return when(busStop) {
            "164000396" -> listOf() // 인입 1번 출구
            "164000395" -> listOf("8", "16", "99", "41") // 인입 2번 출구
            "164000403" -> listOf("6-1")
            "164000380" -> listOf("99")
            "164000385" -> listOf("8", "16", "99") // 정문 -> 인입
            "164000377" -> listOf("8", "6-1") // 공대 -> 인입, 지정단
            else -> throw Exception("no bus stop id")
        }
    }
}