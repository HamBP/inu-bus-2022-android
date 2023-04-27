package org.algosketch.inubus.presentation.ui.toschool

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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
        ) : State()
        data class Error(val message: String) : State()
    }

    val currentTime = MutableStateFlow("")
    val busList = MutableStateFlow<List<BusArrivalInfo>>(listOf())
    val filter = MutableStateFlow("전체")
    val sort = MutableStateFlow("최신순")
    val state: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    private fun getCurrentDateTime(): String {
        val dateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

        return dateTime.format(formatter)
    }

    fun updateBusList(where: String) { // 1 : 인천대입구, 2 : 지식정보단지, 3 : 정문, 4 : 공과대
        state.value = State.Loading
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

            viewModelScope.launch {
                state.value = State.Error("Network Error!")
            }

            throwable.printStackTrace()
        }

        viewModelScope.launch(coroutineExceptionHandler) {
            busList.value = getBusArrivalsUseCase(where).filter { busInfo ->
                (filter.value == "전체") || busInfo.stopStations.contains (filter.value)
            }.sortedList()
            currentTime.value = getCurrentDateTime()

            state.value = if(busList.value.isEmpty()) State.Empty
            else State.Success(
                time = currentTime.value,
                list = busList.value,
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