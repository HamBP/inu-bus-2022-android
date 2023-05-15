package org.algosketch.inubus.data.repository

import org.algosketch.inubus.data.datasource.DummyDataSource
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository
import org.algosketch.inubus.domain.repository.BusStop

class BusArrivalDummyInfoRepository constructor(
    private val dummyDataSource: DummyDataSource
) : BusArrivalInfoRepository {

    override suspend fun getBusArrival(busStop: BusStop): BusArrivalResponse {

        return dummyDataSource.getArrivalBusTime(busStop.toBusId())
    }
}