package org.algosketch.inubus.feature.home

import android.os.Bundle
import android.view.View
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
import org.algosketch.inubus.global.base.BaseFragment
import org.algosketch.inubus.global.usecase.GetBusArrivalTimeUseCase
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResourceId = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModels()

    private val getBusArrivalTimeUseCase: GetBusArrivalTimeUseCase by inject()

    override fun initDataBinding() {
        binding.viewModel = viewModel

        viewModel.startNextFragment.observe(this, Observer {
            findNavController().navigate(R.id.action_home_to_detail)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            val busArrival = getBusArrivalTimeUseCase.run()

        }

        val busList = view.findViewById<RecyclerView>(R.id.bus_list)
        busList.adapter = BusListAdapter(listOf(BusArrival("16", 5, 15), BusArrival("58", 6, 14)))
    }
}