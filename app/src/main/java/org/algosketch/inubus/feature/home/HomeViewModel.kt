package org.algosketch.inubus.feature.home

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.algosketch.inubus.data.model.BusInformation
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.usecase.GetBusArrivalTimeUseCase
import org.algosketch.inubus.global.util.BusInformationUtil
import org.algosketch.inubus.global.util.SingleLiveEvent
import org.koin.core.component.inject
import java.time.LocalDateTime

class HomeViewModel : BaseViewModel() {
    val startNextFragment = SingleLiveEvent<Any>()
    val currentTime = MutableLiveData<String>()
    val busList = MutableLiveData<List<BusInformation>>()

    private val getBusArrivalTimeUseCase: GetBusArrivalTimeUseCase by inject()

    fun startDetail() {
        startNextFragment.call()
    }

    fun refresh() {
        val dateTime = LocalDateTime.now().plusHours(9)
        val dateString = "${dateTime.hour}:${dateTime.minute}"
        currentTime.postValue("${dateString} 기준")
    }

    suspend fun updateBusList(type: Int) { // 1 : 인입, 2 : 지정단
        val list = fetchBIT()
        busList.postValue(list)
    }

    suspend fun fetchINU() = coroutineScope {
        withContext(Dispatchers.Default) {
            BusInformationUtil.transferBusData(getBusArrivalTimeUseCase.run("164000395")) +
                    BusInformationUtil.transferBusData(getBusArrivalTimeUseCase.run("164000396"))
        }
    }

    suspend fun fetchBIT() = coroutineScope {
        withContext(Dispatchers.Default) {
            BusInformationUtil.transferBusData(getBusArrivalTimeUseCase.run("164000403")) +
                    BusInformationUtil.transferBusData(getBusArrivalTimeUseCase.run("164000380"))
        }
    }
}