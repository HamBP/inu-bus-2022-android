package org.algosketch.inubus.ui.home

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import org.algosketch.inubus.domain.entity.BusArrival

@BindingAdapter("app:items")
fun busLists(recyclerView: RecyclerView?, items: MutableLiveData<List<BusArrival>>?) {
    items?.let {
        (recyclerView?.adapter as BusListAdapter).submitList(items.value)
    }
}

@BindingAdapter("app:currentTab")
fun bindTab(viewPager: ViewPager2, tab: MutableLiveData<Int>) {
    viewPager.currentItem = tab.value!!
}