package org.algosketch.inubus.feature.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.algosketch.inubus.R
import org.algosketch.inubus.data.model.BusArrival
import org.algosketch.inubus.databinding.FragmentHomeBinding
import org.algosketch.inubus.feature.wrap.WrapFragmentDirections
import org.algosketch.inubus.global.base.BaseFragment
import org.algosketch.inubus.global.store.Store
import org.algosketch.inubus.global.usecase.GetBusArrivalTimeUseCase
import org.algosketch.inubus.global.util.BusInformationUtil
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResourceId = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModels()

    private val getBusArrivalTimeUseCase: GetBusArrivalTimeUseCase by inject()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.busList.observe(this, {
            val busList = view.findViewById<RecyclerView>(R.id.bus_list)
            busList.adapter = BusListAdapter(it)
        })

        Store.where.observe(this, {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.updateBusList(it)
            }
        })
    }
}