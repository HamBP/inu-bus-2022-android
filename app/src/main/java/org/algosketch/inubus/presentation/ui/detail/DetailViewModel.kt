package org.algosketch.inubus.presentation.ui.detail

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.algosketch.inubus.common.base.BaseViewModel
import org.algosketch.inubus.common.util.SingleLiveEvent

class DetailViewModel : BaseViewModel() {
    val busNumber = MutableStateFlow("")
//    val busNumber = _busNumber.asStateFlow()

    val restTime = MutableStateFlow("")
    val where = MutableStateFlow("")
    val exit = MutableStateFlow("")
    val distance = MutableStateFlow("")
    val imageId = MutableStateFlow<Int?>(null)
    val undoEvent = SingleLiveEvent<Any>()

    fun undo() {
        undoEvent.call()
    }
}