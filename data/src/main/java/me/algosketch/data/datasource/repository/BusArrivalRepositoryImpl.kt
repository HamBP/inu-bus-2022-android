package me.algosketch.data.datasource.repository

import me.algosketch.data.datasource.local.BusArrivalLocalDataSource
import me.algosketch.data.datasource.model.response.BusArrivalResponse
import me.algosketch.data.datasource.remote.BusArrivalRemoteDataSource

class BusArrivalRepositoryImpl(
    private val localDataSource: BusArrivalLocalDataSource,
    private val remoteDataSource: BusArrivalRemoteDataSource,
) {
    // fixme : busStop은 원래 domain의 BusStop이었음.
    suspend fun getBusArrival(busStop: String): BusArrivalResponse {
//        val busStopId = busStop.toBusId()
        val busStopId = busStop

        if (localDataSource.isCached(busStopId))
            return localDataSource.getBusArrival(busStopId)

        val result = remoteDataSource.getBusArrival(busStopId)
        localDataSource.storeData(busStopId, result)
        return result
    }
}