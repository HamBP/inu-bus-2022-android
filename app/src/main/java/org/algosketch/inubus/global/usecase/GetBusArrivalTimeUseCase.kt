package org.algosketch.inubus.global.usecase

import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.data.repository.Repository

class GetBusArrivalTimeUseCase(private val repository: Repository) {
    suspend fun run(bstop: String): BusArrival {
        return repository.getArrivalBusTime(bstop)
    }
}