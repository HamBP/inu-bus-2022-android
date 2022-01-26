package org.algosketch.inubus.data.datasource

import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.global.di.BusArrivalService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteDataSource : DataSource, KoinComponent {
    val service: BusArrivalService by inject()

    override suspend fun getArrivalBusTime(bstopId: String): BusArrival {
        val result = service.getBusArrivalTime(bstopId = bstopId)

        try {
            return result.body()!!
        } catch (err: Exception) {
            throw Exception("UNKNOWN ERROR!!")
        }
    }
}