package org.algosketch.inubus.feature.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.algosketch.inubus.data.repository.RemoteRepository
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.store.Store
import org.algosketch.inubus.global.util.SingleLiveEvent
import org.koin.core.component.inject
import java.time.LocalDateTime

class HomeViewModel : BaseViewModel() {
    val startNextFragment = SingleLiveEvent<Any>()
    val currentTime = MutableLiveData<String>()

    fun startDetail() {
        startNextFragment.call()
    }

    fun refresh() {
        val dateTime = LocalDateTime.now().plusHours(9)
        val dateString = "${dateTime.hour}:${dateTime.minute}"
        currentTime.postValue("${dateString} 기준")
    }
}