package me.algosketch.data.datasource

import me.algosketch.data.datasource.model.response.BusArrivalResponse

interface BusArrivalDataSource {
    suspend fun getBusArrival(bstopId: String) : BusArrivalResponse
}