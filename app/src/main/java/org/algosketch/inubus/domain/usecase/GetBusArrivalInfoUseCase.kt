package org.algosketch.inubus.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.algosketch.inubus.data.model.BusArrivalResponse
import org.algosketch.inubus.domain.repository.BusArrivalInfoRepository

class GetBusArrivalInfoUseCase(private val infoRepository: BusArrivalInfoRepository) {
    suspend operator fun invoke(bstop: String): BusArrivalResponse = withContext(Dispatchers.IO) {
        infoRepository.getArrivalBusTime(bstop)
    }
}