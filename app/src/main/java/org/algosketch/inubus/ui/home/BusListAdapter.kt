package org.algosketch.inubus.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.algosketch.inubus.R
import androidx.core.graphics.drawable.DrawableCompat

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.os.bundleOf
import org.algosketch.inubus.databinding.ItemInformationBinding
import org.algosketch.inubus.domain.entity.BusArrival
import org.algosketch.inubus.global.util.Bus
import org.algosketch.inubus.global.util.BusNumberBackgroundTintUtil


class BusListAdapter(val list: List<BusArrival>) : RecyclerView.Adapter<BusListAdapter.BusViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BusViewHolder.from(parent)
    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        holder.bind(list[position])
    }
    override fun getItemCount() = list.size

    class BusViewHolder private constructor(val binding: ItemInformationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusArrival) {
            binding.item = item

            BusNumberBackgroundTintUtil.setBusNumberBackgroundTint(binding.busNumber, item.busNumber)

            binding.root.setOnClickListener {
                it.findNavController().navigate(R.id.action_wrap_to_detail, getBundle(item))
            }
            binding.tagRecyclerView.adapter = TagAdapter(Bus.getBusStopsByBusNumber(item.busNumber))
            binding.executePendingBindings()
        }

        fun getBundle(busArrival: BusArrival) : Bundle {
            val bundle = bundleOf(
                "exit" to busArrival.exit,
                "where" to busArrival.where,
                "busNumber" to busArrival.busNumber,
                "distance" to Bus.getDistance(busArrival.where, busArrival.busNumber),
                "restTime" to busArrival.restTime,
                "busColor" to Bus.getBusColorByBusNumber(busArrival.busNumber)
            )

            return bundle
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