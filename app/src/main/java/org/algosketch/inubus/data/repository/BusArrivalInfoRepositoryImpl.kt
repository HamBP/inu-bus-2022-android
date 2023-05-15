package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.data.datasource.RemoteDataSource
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository
import org.algosketch.inubus.domain.repository.BusStop

class BusArrivalInfoRepositoryImpl constructor(
    private val cachedDataSource: CachedDataSource,
    private val remoteDataSource: RemoteDataSource
) : BusArrivalInfoRepository {

    override suspend fun getBusArrival(busStop: BusStop): BusArrivalResponse {
        val busStopId = busStop.toBusId()

        if (cachedDataSource.isCached(busStopId))
            return cachedDataSource.getArrivalBusTime(busStopId)

        val result = remoteDataSource.getArrivalBusTime(busStopId)
        cachedDataSource.storeData(busStopId, result)
        return result
    }
}