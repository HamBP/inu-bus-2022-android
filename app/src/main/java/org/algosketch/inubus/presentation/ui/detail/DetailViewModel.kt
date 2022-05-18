package org.algosketch.inubus.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import org.algosketch.inubus.common.base.BaseViewModel
import org.algosketch.inubus.common.util.SingleLiveEvent

class DetailViewModel : BaseViewModel() {
    val busNumber = MutableLiveData("???")
    val restTime = MutableLiveData("???")
    val exit = MutableLiveData("???")
    val distance = MutableLiveData("???")
    //val imageId = MutableLiveData<Int>() TODO : 사진 bindingAdapter 로 변경
    val undoEvent = SingleLiveEvent<Any>()

    fun undo() {
        undoEvent.call()
    }
}