package org.algosketch.inubus.data.datasource

import android.util.Log
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.global.di.BusArrivalService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.lang.Exception

class RemoteDataSource : DataSource, KoinComponent {
    val service: BusArrivalService by inject()

    override suspend fun getArrivalBusTime(bstopId: String) : BusArrival {
        val result = service.getBusArrivalTime(bstopId = bstopId)
        Log.d("네트워크 요청", "url : ${result.raw().request.url} status : ${result.code()}, ${result.body()}")

        try {
            return result.body()!!
        }
        catch (err: Exception) {
            throw Exception("UNKNOWN ERROR!!")
        }
    }
}