package org.algosketch.inubus.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.algosketch.inubus.common.base.BaseViewModel
import org.algosketch.inubus.common.util.SingleLiveEvent
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.usecase.GetBusArrivalInfoUseCase
import org.koin.core.component.inject
import java.net.SocketTimeoutException
import java.time.LocalDateTime

class HomeViewModel : BaseViewModel() {
    val currentTime = MutableStateFlow("")
    val busList = MutableLiveData<List<BusArrivalInfo>>(listOf())
    val eventFlow = MutableSharedFlow<Event>()

    private val getBusArrivalInfoUseCase: GetBusArrivalInfoUseCase by inject()

    private fun refreshTime() {
        currentTime.value = getCurrentDateTime()
    }

    private fun getCurrentDateTime(): String {
        val dateTime = LocalDateTime.now()

        val hourInString = if (dateTime.hour > 9) dateTime.hour.toString() else "0${dateTime.hour}"
        val minuteInString = if (dateTime.minute > 9) dateTime.minute.toString() else "0${dateTime.minute}"
        return "${hourInString}:${minuteInString}"
    }

    fun updateBusList(where: String) { // 1 : 인입, 2 : 지정단
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

            viewModelScope.launch {
                eventFlow.emit(Event.Timeout())
            }

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
        class Timeout : Event()
    }
}