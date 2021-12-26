package org.algosketch.inubus.feature.detail

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.usecase.GetMemoUseCase
import org.algosketch.inubus.global.usecase.WriteMemoUseCase
import org.algosketch.inubus.global.util.SingleLiveEvent
import org.koin.core.component.inject

class DetailViewModel : BaseViewModel() {
    val shouldUndo = SingleLiveEvent<Any>()

    fun undo() {
        shouldUndo.call()
    }
}