package org.algosketch.inubus.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.usecase.GetBusArrivalAssumptionUseCase
import org.algosketch.inubus.domain.usecase.GetBusArrivalUseCase
import org.algosketch.inubus.global.TempDI

class DetailViewModel : ViewModel() {
    private val getBusArrivalUseCase: GetBusArrivalUseCase = TempDI.getBusArrivalUseCase
    val busArrivalInfo = MutableStateFlow(BusArrivalInfo())

    fun fetchData(busNumber: String, busStop: String) {
        when (busStop) {
            "인천대입구", "지식정보단지" -> fetchBusArrival(busNumber = busNumber, busStop = busStop)
            else -> fetchBusArrivalToLeaveSchool(busNumber = busNumber, busStop = busStop)
        }
    }

    private fun fetchBusArrival(busNumber: String, busStop: String) {
        viewModelScope.launch {
            busArrivalInfo.value = getBusArrivalUseCase(busNumber, busStop)
        }
    }

    private fun fetchBusArrivalToLeaveSchool(busNumber: String, busStop: String) {
        viewModelScope.launch {
            busArrivalInfo.value = getBusArrivalUseCase(busNumber, busStop)
        }
    }
}