package org.algosketch.inubus.ui.navigation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.algosketch.inubus.ui.home.HomeFragment
import java.lang.Exception

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> HomeFragment()
            else -> throw Exception("UNKNOWN VIEWPAGER2 POSITION")
        }
    }
}