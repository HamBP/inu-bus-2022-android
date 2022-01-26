package org.algosketch.inubus.ui.detail

import androidx.lifecycle.MutableLiveData
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.util.SingleLiveEvent

class DetailViewModel : BaseViewModel() {
    val shouldUndo = SingleLiveEvent<Any>()
    val busNumber = MutableLiveData("???")
    val restTime = MutableLiveData("???")
    val exit = MutableLiveData("???")
    val distance = MutableLiveData("???")
    //val imageId = MutableLiveData<Int>()

    fun undo() {
        shouldUndo.call()
    }
}