package org.algosketch.inubus.global.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.data.model.BusArrival

class GetBusArrivalTimeUseCase(private val repository: BusArrivalRepository) {
    suspend operator fun invoke(bstop: String): BusArrival = withContext(Dispatchers.IO) {
        repository.getArrivalBusTime(bstop)
    }
}