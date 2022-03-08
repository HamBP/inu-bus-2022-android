package org.algosketch.inubus.domain.repository

import org.algosketch.inubus.data.model.BusArrivalResponse

interface BusArrivalRepository {
    suspend fun getArrivalBusTime(bstopId: String) : BusArrivalResponse
}