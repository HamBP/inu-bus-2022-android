package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository

class BusArrivalInfoRepositoryImpl(
    private val cachedDataSource: CachedDataSource,
    private val remoteDataSource: RemoteDataSource
) : BusArrivalInfoRepository {
    override suspend fun getBusArrival(bstopId: String): BusArrivalResponse {
        if (cachedDataSource.isCached(bstopId))
            return cachedDataSource.getArrivalBusTime(bstopId)

        val result = remoteDataSource.getArrivalBusTime(bstopId)
        cachedDataSource.storeData(bstopId, result)
        return result
    }
}