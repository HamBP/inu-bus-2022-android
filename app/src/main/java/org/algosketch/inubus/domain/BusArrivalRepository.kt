package org.algosketch.inubus.domain

import org.algosketch.inubus.data.model.BusArrival

interface BusArrivalRepository {
    suspend fun getArrivalBusTime(bstopId: String) : BusArrival
}