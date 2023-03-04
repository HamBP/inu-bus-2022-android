package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.datasource.DummyDataSource
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository

class BusArrivalDummyInfoRepository() : BusArrivalInfoRepository {
    val dummyRepository = DummyDataSource()

    override suspend fun getBusArrival(bstopId: String): BusArrivalResponse {
        return dummyRepository.getArrivalBusTime(bstopId)
    }
}