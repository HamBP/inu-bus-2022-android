package org.algosketch.inubus.domain.entity

import androidx.lifecycle.MutableLiveData

data class BusArrival(
    val restTime: Int,
    val busNumber: String,
    val busColor: String,
    val exit: Int,
    val where: String,
    val restTimeInformationText: MutableLiveData<String>,
    val exitInformationText: MutableLiveData<String>
)