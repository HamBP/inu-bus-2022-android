package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.global.usecase.BusArrivalRepository
import java.time.LocalDateTime

class BusArrivalRepositoryImpl(
    private val cachedDataSource: CachedDataSource,
    private val remoteDataSource: RemoteDataSource
) : BusArrivalRepository {
    override suspend fun getArrivalBusTime(bstopId: String): BusArrival {
        val currentTime = LocalDateTime.now()
        val dateString = "${currentTime.dayOfMonth}:${currentTime.hour}:${currentTime.minute}"

        val result: BusArrival?
        if (cachedDataSource.isCached(bstopId)) {
            result = cachedDataSource.getArrivalBusTime(bstopId)
        } else {
            result = remoteDataSource.getArrivalBusTime(bstopId)
            cachedDataSource.storeData(bstopId, result, dateString)
        }
        return result
    }
}