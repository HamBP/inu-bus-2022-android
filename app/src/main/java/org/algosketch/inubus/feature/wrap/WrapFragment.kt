package org.algosketch.inubus.feature.wrap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import org.algosketch.inubus.R

class WrapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wrap, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.home_viewpager)
        viewPager.adapter = HomePagerAdapter(this)

        return view
    }
}