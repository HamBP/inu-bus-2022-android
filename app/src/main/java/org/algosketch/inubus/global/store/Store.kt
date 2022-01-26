package org.algosketch.inubus.global.store

import androidx.lifecycle.MutableLiveData
import org.algosketch.inubus.data.model.BusArrival

object Store {
    var where = MutableLiveData("인천대입구")
    var updatedAt: MutableMap<String, String> = HashMap()
    var cachedBusArrival: MutableMap<String, BusArrival> = HashMap()
}