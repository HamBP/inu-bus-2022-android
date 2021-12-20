package org.algosketch.inubus.feature.home

import org.algosketch.inubus.data.repository.RemoteRepository
import org.algosketch.inubus.global.base.BaseViewModel
import org.algosketch.inubus.global.store.Store
import org.algosketch.inubus.global.util.SingleLiveEvent
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    val startNextFragment = SingleLiveEvent<Any>()

    fun startDetail() {
        startNextFragment.call()
    }
}