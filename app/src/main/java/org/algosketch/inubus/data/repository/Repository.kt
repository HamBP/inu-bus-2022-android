package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.model.BusArrival

interface Repository {
    suspend fun getArrivalBusTime() : BusArrival
}