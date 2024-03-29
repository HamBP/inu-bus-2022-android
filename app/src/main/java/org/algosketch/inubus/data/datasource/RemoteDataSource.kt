package org.algosketch.inubus.data.datasource

import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.data.api.BusArrivalService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val service: BusArrivalService
) : DataSource {

    override suspend fun getArrivalBusTime(bstopId: String): BusArrivalResponse {
        val result = service.getBusArrivalTime(bstopId = bstopId)

        try {
            return result.body()!!
        } catch (err: Exception) {
            throw Exception("UNKNOWN ERROR!!")
        }
    }
}