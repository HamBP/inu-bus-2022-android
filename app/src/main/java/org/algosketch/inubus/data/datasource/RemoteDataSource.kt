package org.algosketch.inubus.data.datasource

import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.data.api.BusArrivalService
import org.algosketch.inubus.di.factory.RetrofitServiceFactory
import org.algosketch.inubus.global.TempDI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteDataSource : DataSource, KoinComponent {
    val service: BusArrivalService = RetrofitServiceFactory.create<BusArrivalService>()

    override suspend fun getArrivalBusTime(bstopId: String): BusArrivalResponse {
        val result = service.getBusArrivalTime(bstopId = bstopId)

        try {
            return result.body()!!
        } catch (err: Exception) {
            throw Exception("UNKNOWN ERROR!!")
        }
    }
}