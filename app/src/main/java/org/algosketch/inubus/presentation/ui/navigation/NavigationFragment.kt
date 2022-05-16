package org.algosketch.inubus.presentation.ui.navigation

import androidx.fragment.app.viewModels
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentNavigationBinding
import org.algosketch.inubus.common.base.BaseFragment
import org.algosketch.inubus.presentation.adapter.HomePagerAdapter

class NavigationFragment : BaseFragment<FragmentNavigationBinding>() {
    override val layoutResourceId = R.layout.fragment_navigation
    private val viewModel: NavigationViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        binding.homeViewpager.adapter = HomePagerAdapter(this)
    }
}