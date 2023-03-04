package org.algosketch.inubus.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.algosketch.inubus.presentation.ui.detail.DetailViewModel
import org.algosketch.inubus.presentation.ui.home.ToSchoolViewModel

object ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ToSchoolViewModel::class.java) -> ToSchoolViewModel() as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel() as T
            else -> throw IllegalArgumentException()
        }
    }
}