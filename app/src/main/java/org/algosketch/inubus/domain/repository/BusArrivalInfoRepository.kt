package org.algosketch.inubus.domain.repository

import org.algosketch.inubus.data.model.BusArrivalResponse

interface BusArrivalInfoRepository {
    suspend fun getBusArrival(busStop: BusStop) : BusArrivalResponse
}