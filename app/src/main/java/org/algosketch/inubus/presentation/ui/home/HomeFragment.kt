package org.algosketch.inubus.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
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
            sendLogToFirebase()
        }
    }

    private fun setupObserver() {
        viewModel.timeEvent.observe(this) {
            findNavController().navigate(R.id.action_wrap_to_timeout)
        }
    }

    private fun sendLogToFirebase() {
        val analytics = FirebaseAnalytics.getInstance(requireContext())
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, "pull to refresh")
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "HomeFragment")
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }
}