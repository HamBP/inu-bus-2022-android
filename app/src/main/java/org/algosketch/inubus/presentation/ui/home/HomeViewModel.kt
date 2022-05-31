package org.algosketch.inubus.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.algosketch.inubus.common.base.BaseViewModel
import org.algosketch.inubus.common.util.SingleLiveEvent
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.usecase.GetBusArrivalInfoUseCase
import org.koin.core.component.inject
import java.time.LocalDateTime

class HomeViewModel : BaseViewModel() {
    val currentTime = MutableLiveData<String>()
    val busList = MutableLiveData<List<BusArrivalInfo>>()
    val timeEvent = SingleLiveEvent<Any>()
    val moveDetailEvent = MutableSharedFlow<BusArrivalInfo>()

    private val getBusArrivalInfoUseCase: GetBusArrivalInfoUseCase by inject()

    private fun refreshTime() {
        val dateTime = LocalDateTime.now()
        currentTime.postValue("${getDateString(dateTime.hour, dateTime.minute)} 기준")
    }

    private fun getDateString(hour: Int, minute: Int): String {
        val hourInString = if (hour > 9) hour.toString() else "0${hour}"
        val minuteInString = if (minute > 9) minute.toString() else "0${minute}"
        return "${hourInString}:${minuteInString}"
    }

    fun updateBusList(where: String) { // 1 : 인입, 2 : 지정단
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            timeEvent.call()
            throwable.printStackTrace()
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            busList.value = getBusArrivalInfoUseCase(where).map { item ->
                item.copy(navigateDetail = {
                    moveDetail(item)
                })
            }
            refreshTime()
        }
    }

    private fun moveDetail(item: BusArrivalInfo) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moveDetailEvent.emit(item)
            }
        }
    }

    fun refresh(refreshLayout: SwipeRefreshLayout, where: String) {
        viewModelScope.launch {
            updateBusList(where)
            refreshLayout.isRefreshing = false
        }
    }
}