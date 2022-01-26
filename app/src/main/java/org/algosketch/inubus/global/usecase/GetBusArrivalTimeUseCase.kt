package org.algosketch.inubus.global.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.data.model.BusArrival

class GetBusArrivalTimeUseCase(private val repository: BusArrivalRepository) {
    suspend fun run(bstop: String): BusArrival = withContext(Dispatchers.IO) {
        repository.getArrivalBusTime(bstop)
    }
}