package me.algosketch.data.datasource.remote

import me.algosketch.data.datasource.BusArrivalDataSource
import me.algosketch.data.datasource.model.response.BusArrivalResponse

class BusArrivalRemoteDataSource constructor(
    private val service: BusArrivalService
) : BusArrivalDataSource {

    override suspend fun getBusArrival(bstopId: String): BusArrivalResponse {
        val result = service.getBusArrivalTime(bstopId = bstopId)

        try {
            return result.body()!!
        } catch (err: Exception) {
            throw Exception("UNKNOWN ERROR!!")
        }
    }
}