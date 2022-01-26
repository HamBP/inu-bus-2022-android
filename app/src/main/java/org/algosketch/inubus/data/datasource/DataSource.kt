package org.algosketch.inubus.data.datasource

import org.algosketch.inubus.data.model.BusArrival

interface DataSource {
    suspend fun getArrivalBusTime(bstopId: String) : BusArrival
}