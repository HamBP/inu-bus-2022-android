package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.repository.BusArrivalRepository

class BusArrivalRepositoryImpl(
    private val cachedDataSource: CachedDataSource,
    private val remoteDataSource: RemoteDataSource
) : BusArrivalRepository {
    override suspend fun getArrivalBusTime(bstopId: String): BusArrivalResponse {
        if (cachedDataSource.isCached(bstopId))
            return cachedDataSource.getArrivalBusTime(bstopId)

        val result = remoteDataSource.getArrivalBusTime(bstopId)
        cachedDataSource.storeData(bstopId, result)
        return result
    }
}