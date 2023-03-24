package org.algosketch.inubus.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.algosketch.inubus.presentation.ui.detail.DetailViewModel
import org.algosketch.inubus.presentation.ui.leaveschool.LeaveSchoolViewModel
import org.algosketch.inubus.presentation.ui.toschool.ToSchoolViewModel

object ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ToSchoolViewModel::class.java) -> ToSchoolViewModel() as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel() as T
            modelClass.isAssignableFrom(LeaveSchoolViewModel::class.java) -> LeaveSchoolViewModel() as T
            else -> throw IllegalArgumentException()
        }
    }
}