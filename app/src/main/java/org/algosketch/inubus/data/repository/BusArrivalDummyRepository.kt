package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.datasource.DummyDataSource
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.BusArrivalRepository

class BusArrivalDummyRepository() : BusArrivalRepository {
    val dummyRepository = DummyDataSource()

    override suspend fun getArrivalBusTime(bstopId: String): BusArrivalResponse {
        return dummyRepository.getArrivalBusTime(bstopId)
    }
}