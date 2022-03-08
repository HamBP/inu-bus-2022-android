package org.algosketch.inubus.presentation.ui.navigation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val currentTab = MutableLiveData(0)
}