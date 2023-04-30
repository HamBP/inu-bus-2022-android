package org.algosketch.inubus.presentation.ui.toschool

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.usecase.GetBusArrivalsUseCase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ToSchoolViewModel @Inject constructor(
    private val getBusArrivalsUseCase: GetBusArrivalsUseCase
) : ViewModel() {
    sealed class State {
        object Empty : State()
        object Loading : State()
        data class Success(
            val time: String,
            val list: List<BusArrivalInfo>,
            val filter: String,
            val sort: String = "최신순"
        ) : State()
        data class Error(val message: String) : State()
    }

    sealed class Event {
        object Refresh : Event()
    }

    val filter = MutableStateFlow("전체")
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state = _state.asStateFlow()

    private val _eventsFlow = MutableSharedFlow<Event>()
    val eventsFlow = _eventsFlow.asSharedFlow()

    fun sendEvent(event: Event) {
        viewModelScope.launch {
            _eventsFlow.emit(event)
        }
    }

    private fun getCurrentDateTime(): String {
        val dateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        return dateTime.format(formatter)
    }

    fun updateBusList(where: String) { // 1 : 인천대입구, 2 : 지식정보단지, 3 : 정문, 4 : 공과대
        _state.value = State.Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

            viewModelScope.launch {
                _state.value = State.Error("Network Error!")
            }

            throwable.printStackTrace()
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            val busList = getBusArrivalsUseCase(where).filter { busInfo ->
                (filter.value == "전체") || busInfo.stopStations.contains (filter.value)
            }.sortedList()

            // FIXME : loading 값이 false(원래 값) -> true(새로고침) -> false(결과) 가 너무 빠르게 발생하면 true일 때 리컴포지션이 동작하지 않는 버그가 있습니다.
            // FIXME : true가 생략되면 PullRefreshState.kt 80라인 state.setRefreshing(refreshing) 코드가 호출되지 않으면서 refresh 아이콘 잔상이 생깁니다.
            // FIXME : 이 현상을 해결하기 위해 임의로 delay를 주었으나 올바른 해결 방법이 아닙니다.
            delay(100)

            _state.value = if(busList.isEmpty()) State.Empty
            else State.Success(
                time = getCurrentDateTime(),
                list = busList,
                filter = filter.value,
            )
        }
    }

    private fun List<BusArrivalInfo>.sortedList(): List<BusArrivalInfo> {
        return this.sortedBy { busArrivalInfo ->
            busArrivalInfo.restTime
        }
    }
}