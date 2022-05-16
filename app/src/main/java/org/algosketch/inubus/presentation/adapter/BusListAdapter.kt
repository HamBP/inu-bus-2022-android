package org.algosketch.inubus.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.algosketch.inubus.R

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.algosketch.inubus.databinding.ItemInformationBinding
import org.algosketch.inubus.domain.entity.BusArrivalInfo
import org.algosketch.inubus.common.util.Bus
import org.algosketch.inubus.common.util.BusNumberBackgroundTintUtil
import org.algosketch.inubus.presentation.adapter.TagAdapter

class BusListAdapter : ListAdapter<BusArrivalInfo, BusListAdapter.BusViewHolder>(BusListDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BusViewHolder.from(parent)
    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BusViewHolder private constructor(val binding: ItemInformationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusArrivalInfo) {
            binding.item = item

            BusNumberBackgroundTintUtil.setBusNumberBackgroundTint(binding.busNumber, item.busNumber)

            binding.root.setOnClickListener {
                it.findNavController().navigate(R.id.action_wrap_to_detail, item.toBundle())
            }
            binding.tagRecyclerView.adapter = TagAdapter(Bus.getBusStopsByBusNumber(item.busNumber))
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : BusViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemInformationBinding.inflate(layoutInflater, parent, false)

                return BusViewHolder(binding)
            }
        }
    }
}

class BusListDiffUtil : DiffUtil.ItemCallback<BusArrivalInfo>() {
    override fun areItemsTheSame(oldItem: BusArrivalInfo, newItem: BusArrivalInfo): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: BusArrivalInfo, newItem: BusArrivalInfo): Boolean {
        return oldItem == newItem
    }
}