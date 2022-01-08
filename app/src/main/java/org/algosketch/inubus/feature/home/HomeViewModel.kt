package org.algosketch.inubus.feature.home

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.algosketch.inubus.data.model.BusInformation
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.usecase.GetBusArrivalTimeUseCase
import org.algosketch.inubus.global.util.BusInformationUtil
import org.algosketch.inubus.global.util.SingleLiveEvent
import org.koin.core.component.inject
import java.time.LocalDateTime

class HomeViewModel : BaseViewModel() {
    val currentTime = MutableLiveData<String>()
    val busList = MutableLiveData<List<BusInformation>>()

    private val getBusArrivalTimeUseCase: GetBusArrivalTimeUseCase by inject()

    fun refreshTime() {
        val dateTime = LocalDateTime.now()
        val dateString = "${dateTime.hour}:${dateTime.minute}"
        currentTime.postValue("${dateString} 기준")
    }

    suspend fun updateBusList(where: String) { // 1 : 인입, 2 : 지정단
        val list = if(where == "인천대입구") fetchINU() else fetchBIT()
        busList.postValue(list)
        refreshTime()
    }

    suspend fun fetchINU() = coroutineScope {
        withContext(Dispatchers.Default) {
            val list1 = BusInformationUtil.transferBusData(getBusArrivalTimeUseCase.run("164000395"))
            val list2 = BusInformationUtil.transferBusData(getBusArrivalTimeUseCase.run("164000396"))
            list1 + list2
        }
    }

    suspend fun fetchBIT() = coroutineScope {
        withContext(Dispatchers.Default) {
            val list1 = BusInformationUtil.transferBusData(getBusArrivalTimeUseCase.run("164000403"))
            val list2 = BusInformationUtil.transferBusData(getBusArrivalTimeUseCase.run("164000380"))
            list1 + list2
        }
    }
}