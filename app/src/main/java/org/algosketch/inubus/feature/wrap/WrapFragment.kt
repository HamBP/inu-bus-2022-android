package org.algosketch.inubus.feature.wrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import org.algosketch.inubus.R
import org.algosketch.inubus.global.store.Store
import java.lang.Exception

class WrapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wrap, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.home_viewpager)
        viewPager.adapter = HomePagerAdapter(this)
        viewPager.isUserInputEnabled = false

        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    Store.where.postValue("인천대입구")
                } else {
                    Store.where.postValue("지식정보단지")
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        return view
    }
}