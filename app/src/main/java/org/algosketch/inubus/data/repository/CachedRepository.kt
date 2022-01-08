package org.algosketch.inubus.data.repository

import android.util.Log
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.global.store.Store
import org.algosketch.inubus.global.util.BusInformationUtil
import java.lang.Exception

class CachedRepository : Repository {
    override suspend fun getArrivalBusTime(bstopId: String): BusArrival {
        Log.d("캐시 요청", bstopId)
        return Store.cachedBusArrival[bstopId] ?: BusArrival(null, null, null)
    }
}