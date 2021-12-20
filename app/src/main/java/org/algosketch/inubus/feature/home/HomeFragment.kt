package org.algosketch.inubus.feature.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentHomeBinding
import org.algosketch.inubus.global.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResourceId = R.layout.fragment_home
    private val viewModel: HomeViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel

        viewModel.startNextFragment.observe(this, Observer {
            findNavController().navigate(R.id.action_home_to_memo)
        })
    }
}