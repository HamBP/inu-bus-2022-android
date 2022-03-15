package org.algosketch.inubus.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.algosketch.inubus.presentation.ui.home.HomeFragment
import java.lang.Exception

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment("인천대입구")
            1 -> HomeFragment("지식정보단지")
            else -> throw Exception("UNKNOWN VIEWPAGER2 POSITION")
        }
    }
}