package org.algosketch.inubus.ui.home

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import org.algosketch.inubus.domain.entity.BusArrival

@BindingAdapter("app:items")
fun busLists(recyclerView: RecyclerView?, items: MutableLiveData<List<BusArrival>>?) {
    println("로그를 찍어보자")
    println("리사이클러뷰 : $recyclerView")
    println("아이템 : $items")
    items?.let {
        (recyclerView?.adapter as BusListAdapter).submitList(items)
    }
}