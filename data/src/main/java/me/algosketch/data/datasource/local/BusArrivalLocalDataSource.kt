package me.algosketch.data.datasource.local

import android.util.Log
import me.algosketch.data.datasource.BusArrivalDataSource
import me.algosketch.data.datasource.model.response.BusArrivalResponse
import java.lang.ref.WeakReference
import java.time.LocalDateTime

class BusArrivalLocalDataSource : BusArrivalDataSource {
    private val expiredAt: MutableMap<String, LocalDateTime> = HashMap()
    private val cachedBusArrivalResponse: MutableMap<String, WeakReference<BusArrivalResponse>> = HashMap()

    override suspend fun getBusArrival(bstopId: String): BusArrivalResponse {
        Log.d("$bstopId 에 대한 캐시 요청", "메모리에 저장된 데이터를 반환합니다.")
        return cachedBusArrivalResponse[bstopId]?.get() ?: BusArrivalResponse(null, null, null)
    }

    fun storeData(bstopId: String, busArrivalResponse: BusArrivalResponse) {
        cachedBusArrivalResponse[bstopId] = WeakReference(busArrivalResponse)
        expiredAt[bstopId] = LocalDateTime.now().plusSeconds(10)
    }

    fun isCached(bstopId: String) : Boolean {
        val now = LocalDateTime.now()
        return expiredAt[bstopId]?.isAfter(now) ?: false && cachedBusArrivalResponse[bstopId]?.get() != null
    }
}