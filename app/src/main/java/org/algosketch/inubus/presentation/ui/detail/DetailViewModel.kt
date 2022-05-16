package org.algosketch.inubus.presentation.ui.detail

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import org.algosketch.inubus.common.base.BaseViewModel
import org.algosketch.inubus.common.util.SingleLiveEvent

class DetailViewModel : BaseViewModel() {
    val busNumber = MutableLiveData("???")
    val restTime = MutableLiveData("???")
    val exit = MutableLiveData("???")
    val distance = MutableLiveData("???")
    //val imageId = MutableLiveData<Int>()

    fun undo(view: View) {
        view.findNavController().navigateUp()
    }
}