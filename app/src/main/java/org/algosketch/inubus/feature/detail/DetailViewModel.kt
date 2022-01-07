package org.algosketch.inubus.feature.detail

import androidx.lifecycle.MutableLiveData
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.util.SingleLiveEvent

class DetailViewModel : BaseViewModel() {
    val shouldUndo = SingleLiveEvent<Any>()
    val information = MutableLiveData("???")

    fun undo() {
        shouldUndo.call()
    }
}