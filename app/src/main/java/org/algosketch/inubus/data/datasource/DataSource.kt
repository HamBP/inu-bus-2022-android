package org.algosketch.inubus.data.datasource

import org.algosketch.inubus.data.model.BusArrivalResponse

interface DataSource {
    suspend fun getArrivalBusTime(bstopId: String) : BusArrivalResponse
}