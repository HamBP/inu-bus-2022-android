package org.algosketch.inubus.global.usecase

import org.algosketch.inubus.data.model.BusArrival

interface BusArrivalRepository {
    suspend fun getArrivalBusTime(bstopId: String) : BusArrival
}