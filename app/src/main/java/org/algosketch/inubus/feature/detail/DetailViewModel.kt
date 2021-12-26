package org.algosketch.inubus.feature.detail

import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.util.SingleLiveEvent

class DetailViewModel : BaseViewModel() {
    val shouldUndo = SingleLiveEvent<Any>()

    fun undo() {
        shouldUndo.call()
    }
}