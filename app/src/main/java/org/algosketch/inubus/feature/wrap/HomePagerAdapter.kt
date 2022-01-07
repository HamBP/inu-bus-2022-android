package org.algosketch.inubus.feature.wrap

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.algosketch.inubus.feature.home.HomeFragment
import java.lang.Exception

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return HomeFragment()
    }
}