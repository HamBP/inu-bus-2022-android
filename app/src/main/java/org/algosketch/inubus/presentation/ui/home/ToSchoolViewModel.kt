package org.algosketch.inubus.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.algosketch.inubus.common.base.BaseViewModel
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.usecase.GetBusArrivalInfoUseCase
import org.algosketch.inubus.global.TempDI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ToSchoolViewModel : BaseViewModel() {
    val currentTime = MutableStateFlow("")
    val busList = MutableLiveData<List<BusArrivalInfo>>(listOf())
    val eventFlow = MutableSharedFlow<Event>()
    val filter = MutableStateFlow("전체")
    val sort = MutableStateFlow("최신순")

    private val getBusArrivalInfoUseCase: GetBusArrivalInfoUseCase = TempDI.getBusArrivalInfoUseCase

    private fun refreshTime() {
        currentTime.value = getCurrentDateTime()
    }

    private fun getCurrentDateTime(): String {
        val dateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        return dateTime.format(formatter)
    }

    fun updateBusList(where: String) { // 1 : 인입, 2 : 지정단
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

            viewModelScope.launch {
                eventFlow.emit(Event.Timeout)
            }

            throwable.printStackTrace()
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            busList.value = getBusArrivalInfoUseCase(where).map { item ->
                item.copy(navigateDetail = {
                    moveDetail(item)
                })
            }.filter { busInfo ->
                (filter.value == "전체") ||
                        (Bus.getBusStopsByBusNumber(busInfo.busNumber).find { busStop ->
                    busStop == filter.value
                } != null)
            }.sortedList()
            refreshTime()
        }
    }

    private fun moveDetail(item: BusArrivalInfo) {
        viewModelScope.launch {
            eventFlow.emit(Event.MoveDetail(item))
        }
    }

    fun refresh(refreshLayout: SwipeRefreshLayout, where: String) {
        viewModelScope.launch {
            updateBusList(where)
            refreshLayout.isRefreshing = false
        }
    }

    sealed class Event {
        data class MoveDetail(val busInfo: BusArrivalInfo) : Event()
        object Timeout : Event()
    }

    private fun List<BusArrivalInfo>.sortedList(): List<BusArrivalInfo> {
        return this.sortedBy { busArrivalInfo ->
            busArrivalInfo.restTime
        }
    }
}