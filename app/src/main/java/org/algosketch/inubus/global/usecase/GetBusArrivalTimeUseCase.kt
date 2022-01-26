package org.algosketch.inubus.global.usecase

import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.data.datasource.CachedDataSource
import org.algosketch.inubus.global.store.Store
import java.time.LocalDateTime

class GetBusArrivalTimeUseCase(private val repository: BusArrivalRepository) {
    suspend fun run(bstop: String): BusArrival {
        val dateTime = LocalDateTime.now()
        val dateString = "${dateTime.dayOfMonth}:${dateTime.hour}:${dateTime.minute}"

        return if(dateString == Store.updatedAt[bstop]) CachedDataSource().getArrivalBusTime(bstop)
        else {
            val result = repository.getArrivalBusTime(bstop)
            Store.cachedBusArrival[bstop] = result
            Store.updatedAt[bstop] = dateString

            result
        }
    }
}