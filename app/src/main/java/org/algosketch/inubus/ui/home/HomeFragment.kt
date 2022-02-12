package org.algosketch.inubus.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentHomeBinding
import org.algosketch.inubus.global.base.BaseFragment
import org.algosketch.inubus.global.store.Store

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResourceId = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        viewModel.busList.observe(this, {
            binding.busList.adapter = BusListAdapter(it)
        })

        Store.where.observe(this, {
            viewModel.updateBusList(it)
        })
    }
}