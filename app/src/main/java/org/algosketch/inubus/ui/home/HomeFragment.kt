package org.algosketch.inubus.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.internal.notify
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentHomeBinding
import org.algosketch.inubus.global.base.BaseFragment
import org.algosketch.inubus.global.store.Store
import org.algosketch.inubus.ui.error.ErrorActivity
import org.algosketch.inubus.ui.main.MainActivity
import java.time.LocalDateTime

class HomeFragment() : BaseFragment<FragmentHomeBinding>() {
    override val layoutResourceId = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        binding.busList.adapter = BusListAdapter()

        Store.where.observe(this) {
            viewModel.updateBusList(it)
        }
    }
}