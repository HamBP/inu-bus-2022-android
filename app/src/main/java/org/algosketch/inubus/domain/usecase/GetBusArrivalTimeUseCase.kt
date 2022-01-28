package org.algosketch.inubus.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.domain.BusArrivalRepository

class GetBusArrivalTimeUseCase(private val repository: BusArrivalRepository) {
    suspend operator fun invoke(bstop: String): BusArrival = withContext(Dispatchers.IO) {
        repository.getArrivalBusTime(bstop)
    }
}