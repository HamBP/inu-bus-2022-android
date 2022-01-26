package org.algosketch.inubus.data.datasource

import android.util.Log
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.global.store.Store

class CachedDataSource : DataSource {
    override suspend fun getArrivalBusTime(bstopId: String): BusArrival {
        Log.d("캐시 요청", bstopId)
        return Store.cachedBusArrival[bstopId] ?: BusArrival(null, null, null)
    }
}