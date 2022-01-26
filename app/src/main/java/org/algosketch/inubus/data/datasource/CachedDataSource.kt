package org.algosketch.inubus.data.datasource

import android.util.Log
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.global.store.Store

class CachedDataSource : DataSource {
    val updatedAt: MutableMap<String, String> = HashMap()
    private val cachedBusArrival: MutableMap<String, BusArrival> = HashMap()

    override suspend fun getArrivalBusTime(bstopId: String): BusArrival {
        Log.d("$bstopId 에 대한 캐시 요청", "메모리에 저장된 데이터를 반환합니다.")
        return cachedBusArrival[bstopId] ?: BusArrival(null, null, null)
    }

    fun storeData(bstopId: String, busArrival: BusArrival, dateString: String) {
        cachedBusArrival[bstopId] = busArrival
        updatedAt[bstopId] = dateString
    }
}