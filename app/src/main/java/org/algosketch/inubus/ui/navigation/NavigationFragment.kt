package org.algosketch.inubus.ui.navigation

import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import org.algosketch.inubus.R
import org.algosketch.inubus.databinding.FragmentNavigationBinding
import org.algosketch.inubus.global.base.BaseFragment
import org.algosketch.inubus.global.store.Store

class NavigationFragment : BaseFragment<FragmentNavigationBinding>() {
    override val layoutResourceId = R.layout.fragment_navigation
    private val viewModel: NavigationViewModel by viewModels()

    override fun initDataBinding() {
        binding.viewModel = viewModel
    }

    override fun initState() {
        binding.homeViewpager.run {
            adapter = HomePagerAdapter(this@NavigationFragment)
            isUserInputEnabled = false
            println(viewModel.currentTab.value)
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.currentTab.value = tab?.position

                if (tab?.position == 0) {
                    Store.where.postValue("인천대입구")
                } else {
                    Store.where.postValue("지식정보단지")
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}