package org.algosketch.inubus.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*
import org.algosketch.inubus.data.datasource.DummyDataSource
import org.algosketch.inubus.domain.entity.BusArrival
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.domain.usecase.GetBusArrivalTimeUseCase
import org.algosketch.inubus.data.mapper.BusArrivalMapper
import org.koin.core.component.inject
import java.time.LocalDateTime

class HomeViewModel : BaseViewModel() {
    val currentTime = MutableLiveData<String>()
    val busList = MutableLiveData<List<BusArrival>>()

    private val getBusArrivalTimeUseCase: GetBusArrivalTimeUseCase by inject()

    fun refreshTime() {
        val dateTime = LocalDateTime.now()
        currentTime.postValue("${getDateString(dateTime.hour, dateTime.minute)} 기준")
    }

    fun getDateString(hour: Int, minute: Int) : String {
        val hourInString = if(hour > 9) hour.toString() else "0${hour}"
        val minuteInString = if(minute > 9) minute.toString() else "0${minute}"
        return "${hourInString}:${minuteInString}"
    }

    fun updateBusList(where: String) { // 1 : 인입, 2 : 지정단
        CoroutineScope(Dispatchers.Main).launch {
            val list = if(where == "인천대입구") fetchINU() else fetchBIT()
            busList.value = list
            refreshTime()
        }
    }

    suspend fun fetchINU() = coroutineScope {
        withContext(Dispatchers.Default) {
            val list1 = BusArrivalMapper.toBusArrival(getBusArrivalTimeUseCase("164000395"))
            val list2 = BusArrivalMapper.toBusArrival(getBusArrivalTimeUseCase("164000396"))
            list1 + list2
        }
    }

    suspend fun fetchBIT() = coroutineScope {
        withContext(Dispatchers.Default) {
            val list1 = BusArrivalMapper.toBusArrival(getBusArrivalTimeUseCase("164000403"))
            val list2 = BusArrivalMapper.toBusArrival(getBusArrivalTimeUseCase("164000380"))
            list1 + list2
        }
    }

    fun refresh(refreshLayout: SwipeRefreshLayout, where: String) {
        CoroutineScope(Dispatchers.Main).launch {
            updateBusList(where)
            refreshLayout.isRefreshing = false
        }
    }
}