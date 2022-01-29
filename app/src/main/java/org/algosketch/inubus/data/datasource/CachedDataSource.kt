package org.algosketch.inubus.data.datasource

import android.util.Log
import org.algosketch.inubus.data.model.BusArrivalResponse
import java.time.LocalDateTime

class CachedDataSource : DataSource {
    val updatedAt: MutableMap<String, String> = HashMap()
    private val cachedBusArrivalResponse: MutableMap<String, BusArrivalResponse> = HashMap()

    override suspend fun getArrivalBusTime(bstopId: String): BusArrivalResponse {
        Log.d("$bstopId 에 대한 캐시 요청", "메모리에 저장된 데이터를 반환합니다.")
        return cachedBusArrivalResponse[bstopId] ?: BusArrivalResponse(null, null, null)
    }

    fun storeData(bstopId: String, busArrivalResponse: BusArrivalResponse) {
        val dateString = getDateString()
        cachedBusArrivalResponse[bstopId] = busArrivalResponse
        updatedAt[bstopId] = dateString
    }

    fun isCached(bstopId: String) : Boolean {
        val dateString = getDateString()
        return dateString == updatedAt[bstopId]
    }

    private fun getDateString(): String {
        val currentTime = LocalDateTime.now()
        val dateString = "${currentTime.dayOfMonth}:${currentTime.hour}:${currentTime.minute}"
        return dateString
    }
}