package org.algosketch.inubus.presentation.ui.home

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import org.algosketch.inubus.domain.entity.BusArrivalInfo

@BindingAdapter("app:items")
fun busLists(recyclerView: RecyclerView?, items: MutableLiveData<List<BusArrivalInfo>>?) {
    items?.let {
        (recyclerView?.adapter as BusListAdapter).submitList(items.value)
    }
}

@BindingAdapter("app:currentTab")
fun bindTab(viewPager: ViewPager2, tab: MutableLiveData<Int>) {
    viewPager.currentItem = tab.value!!
}

@BindingAdapter("app:tabSelection", "android:onTabSelected", requireAll = false)
fun setTab(
    view: TabLayout,
    currentItem: Int,
    onTabSelectedListener: TabLayout.OnTabSelectedListener
) {
    view.getTabAt(currentItem)?.select()
    view.addOnTabSelectedListener(onTabSelectedListener)
}

@BindingAdapter("app:isUserInputEnabled")
fun setUserInputEnabled(view: ViewPager2, isUserInputEnabled: Boolean) {
    view.isUserInputEnabled = isUserInputEnabled
}

@BindingAdapter("app:mapImage")
fun bindMapImage(view: ImageView, resId: Int?) {
    resId?.let {
        view.setImageDrawable(
            ContextCompat.getDrawable(
                view.context, resId
            )
        )
    }
}