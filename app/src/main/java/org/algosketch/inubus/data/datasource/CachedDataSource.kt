package org.algosketch.inubus.data.datasource

import android.util.Log
import org.algosketch.inubus.data.model.BusArrivalResponse
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CachedDataSource @Inject constructor() : DataSource {
    val expiredAt: MutableMap<String, LocalDateTime> = HashMap()
    private val cachedBusArrivalResponse: MutableMap<String, BusArrivalResponse> = HashMap()

    override suspend fun getArrivalBusTime(bstopId: String): BusArrivalResponse {
        Log.d("$bstopId 에 대한 캐시 요청", "메모리에 저장된 데이터를 반환합니다.")
        return cachedBusArrivalResponse[bstopId] ?: BusArrivalResponse(null, null, null)
    }

    fun storeData(bstopId: String, busArrivalResponse: BusArrivalResponse) {
        cachedBusArrivalResponse[bstopId] = busArrivalResponse
        expiredAt[bstopId] = LocalDateTime.now().plusSeconds(10)
    }

    fun isCached(bstopId: String) : Boolean {
        val now = LocalDateTime.now()
        return expiredAt[bstopId]?.isAfter(now) ?: false
    }
}