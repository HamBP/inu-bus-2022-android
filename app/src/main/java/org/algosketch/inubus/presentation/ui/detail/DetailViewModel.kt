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
    private val getBusArrivalAssumptionUseCase: GetBusArrivalAssumptionUseCase = TempDI.getBusArrivalAssumptionUseCase
    val busArrivalInfo = MutableStateFlow(BusArrivalInfo())

    fun fetchBusArrival(busNumber: String, busStop: String) {
        viewModelScope.launch {
            getBusArrivalAssumptionUseCase(busNumber)
            busArrivalInfo.value = getBusArrivalUseCase(busNumber, busStop)
        }
    }
}