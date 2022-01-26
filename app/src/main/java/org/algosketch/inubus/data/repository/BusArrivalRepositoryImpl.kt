package org.algosketch.inubus.data.repository

import android.util.Log
import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.DataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.global.store.Store
import org.algosketch.inubus.global.usecase.BusArrivalRepository
import java.time.LocalDateTime

class BusArrivalRepositoryImpl(
    private val cachedDataSource: CachedDataSource,
    private val remoteDataSource: RemoteDataSource
) : BusArrivalRepository {
    private val updatedAt: MutableMap<String, String> = HashMap()
    private val cachedBusArrival: MutableMap<String, BusArrival> = HashMap()

    override suspend fun getArrivalBusTime(bstopId: String) : BusArrival {
        val currentTime = LocalDateTime.now()
        val dateString = "${currentTime.dayOfMonth}:${currentTime.hour}:${currentTime.minute}"

        val result: BusArrival?
        if(dateString == updatedAt[bstopId]){
            result = cachedBusArrival[bstopId]!!
            Log.d("bstopId - $bstopId", "캐시된 데이터를 반환합니다.")
        }
        else {
            result = remoteDataSource.getArrivalBusTime(bstopId)
            cachedBusArrival[bstopId] = result
            updatedAt[bstopId] = dateString
        }
        return result
    }
}