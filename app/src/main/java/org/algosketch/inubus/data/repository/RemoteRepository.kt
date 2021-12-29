package org.algosketch.inubus.data.repository

import android.util.Log
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.global.di.BusArrivalService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import java.lang.Exception

class RemoteRepository : Repository, KoinComponent {
    val service: BusArrivalService by inject()

    override suspend fun getArrivalBusTime() : BusArrival {
        val result = service.getBusArrivalTime()
        Log.d("네트워크 요청", "url : ${result.raw().request().url()} status : ${result.code()}, ${result.body()}")

        try {
            return BusArrival("5", 5, 5)!!
        }
        catch (err: Exception) {
            throw Exception("UNKNOWN ERROR!!")
        }
    }
}