package org.algosketch.inubus.presentation.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentHomeBinding
import org.algosketch.inubus.global.base.BaseFragment

class HomeFragment(private val where: String) : BaseFragment<FragmentHomeBinding>() {
    override val layoutResourceId = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        setupObserver()

        binding.busList.adapter = BusListAdapter()
        viewModel.updateBusList(where)
    }

    override fun onResume() {
        super.onResume()

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh(binding.refreshLayout, where)
        }
    }

    private fun setupObserver() {
        viewModel.timeEvent.observe(this) {
            findNavController().navigate(R.id.action_wrap_to_timeout)
        }
    }
}