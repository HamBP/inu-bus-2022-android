package org.algosketch.inubus.feature.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.algosketch.inubus.R
import org.algosketch.inubus.data.model.BusInformation
import org.algosketch.inubus.data.repository.RemoteRepository
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.store.Store
import org.algosketch.inubus.global.usecase.GetBusArrivalTimeUseCase
import org.algosketch.inubus.global.util.BusInformationUtil
import org.algosketch.inubus.global.util.SingleLiveEvent
import org.koin.android.ext.android.inject
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

    suspend fun updateBusList() {
        CoroutineScope(Dispatchers.Main).launch {
            val busArrival1 = getBusArrivalTimeUseCase.run("164000395")
            val list1 = BusInformationUtil.transferBusData(busArrival1)
            val busArrival2 = getBusArrivalTimeUseCase.run("164000396")
            val list2 = BusInformationUtil.transferBusData(busArrival2)
            val list = list1 + list2

            busList.postValue(list)
        }
    }
}