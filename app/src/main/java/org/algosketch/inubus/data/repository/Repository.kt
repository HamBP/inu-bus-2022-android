package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.data.model.BusArrivalResponse

interface Repository {
    suspend fun getArrivalBusTime() : BusArrival
}