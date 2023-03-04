package org.algosketch.inubus.presentation.ui.detail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.algosketch.inubus.common.base.BaseViewModel
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.domain.usecase.GetBusArrivalUseCase
import org.algosketch.inubus.global.TempDI

class DetailViewModel : BaseViewModel() {
    private val getBusArrivalUseCase: GetBusArrivalUseCase = TempDI.getBusArrivalUseCase
    val busArrivalInfo = MutableStateFlow(BusArrivalInfo())

    fun fetchBusArrival(busNumber: String, busStop: String) {
        viewModelScope.launch {
            busArrivalInfo.value = getBusArrivalUseCase(busNumber, busStop)
        }
    }
}