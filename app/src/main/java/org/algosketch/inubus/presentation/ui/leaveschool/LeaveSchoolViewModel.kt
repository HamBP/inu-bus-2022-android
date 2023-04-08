package org.algosketch.inubus.presentation.ui.leaveschool

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.usecase.GetBusArrivalsUseCase
import org.algosketch.inubus.global.TempDI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LeaveSchoolViewModel : ViewModel() {
    val currentTime = MutableStateFlow("")
    val busList = MutableStateFlow<List<BusArrivalInfo>>(listOf())
    val eventFlow = MutableSharedFlow<Event>()
    // todo : 하교용 필터 적용
    val filter = MutableStateFlow("전체")
    val sort = MutableStateFlow("최신순")

    private val getBusArrivalsUseCase: GetBusArrivalsUseCase = TempDI.getBusArrivalsUseCase

    private fun refreshTime() {
        currentTime.value = getCurrentDateTime()
    }

    private fun getCurrentDateTime(): String {
        val dateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        return dateTime.format(formatter)
    }

    fun updateBusList(where: String) { // 1 : 인천대입구, 2 : 지식정보단지, 3 : 정문, 4 : 공과대
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

            viewModelScope.launch {
                eventFlow.emit(Event.Timeout)
            }

            throwable.printStackTrace()
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            busList.value = getBusArrivalsUseCase(where).filter { busInfo ->
                (filter.value == "전체") ||
                        (Bus.getBusStopsByBusNumber(busInfo.busNumber).find { busStop ->
                            busStop == filter.value
                        } != null)
            }.sortedList()
            refreshTime()
        }
    }

    sealed class Event {
        object Timeout : Event()
    }

    private fun List<BusArrivalInfo>.sortedList(): List<BusArrivalInfo> {
        return this.sortedBy { busArrivalInfo ->
            busArrivalInfo.restTime
        }
    }
}