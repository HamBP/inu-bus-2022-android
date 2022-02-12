package org.algosketch.inubus.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val currentTab = MutableLiveData(0)
}