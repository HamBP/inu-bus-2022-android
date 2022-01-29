package org.algosketch.inubus.domain

import org.algosketch.inubus.data.model.BusArrivalResponse

interface BusArrivalRepository {
    suspend fun getArrivalBusTime(bstopId: String) : BusArrivalResponse
}