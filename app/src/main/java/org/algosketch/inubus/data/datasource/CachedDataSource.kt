package org.algosketch.inubus.data.datasource

import android.util.Log
import org.algosketch.inubus.data.model.BusArrival
import java.time.LocalDateTime

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

    fun isCached(bstopId: String) : Boolean {
        val currentTime = LocalDateTime.now()
        val dateString = "${currentTime.dayOfMonth}:${currentTime.hour}:${currentTime.minute}"
        return dateString == updatedAt[bstopId]
    }
}