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


class BusListAdapter(val list: List<BusArrival>) : RecyclerView.Adapter<BusListAdapter.BusViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        return BusViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class BusViewHolder private constructor(val view: View, val binding: ItemInformationBinding) : RecyclerView.ViewHolder(view) {
        fun bind(item: BusArrival) {
            binding.item = item

            setBusNumber(binding.busNumber, item.busNumber)

            val where = item.where
            val exit = item.exit
            val restTime = item.restTime
            val distance = Bus.getDistance(where, item.busNumber)
            val busColor = Bus.getBusColorByBusNumber(item.busNumber)

            binding.exit.text = "${where}역 ${exit}번 출구"
            binding.busArrivalTime.text = "버스가 ${restTime}분 뒤 도착해요."
            binding.root.setOnClickListener {
                val navController = it.findNavController()
                val bundle = getBundle(exit, where, item.busNumber, distance, restTime, busColor)
                navController.navigate(R.id.action_wrap_to_detail, bundle)
            }
            binding.tagRecyclerView.adapter = TagAdapter(Bus.getBusStopsByBusNumber(item.busNumber))
        }

        fun getBundle(exit: Int, where: String, busNumber: String, distance: Int, restTime: Int, busColor: String) : Bundle {
            val bundle = bundleOf(
                "exit" to exit,
                "where" to where,
                "busNumber" to busNumber,
                "distance" to distance,
                "restTime" to restTime,
                "busColor" to busColor
            )

            return bundle
        }

        companion object {
            fun from(parent: ViewGroup) : BusViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemInformationBinding.inflate(layoutInflater, parent, false)

                return BusViewHolder(binding.root, binding)
            }
        }
        fun setBusNumber(view: TextView, busNumber: String) {
            val busColor = Bus.getBusColorByBusNumber(busNumber)
            val colorMap = mapOf(
                "red" to R.color.red_bus,
                "blue" to R.color.blue_bus,
                "green" to R.color.green_bus,
                "purple" to R.color.purple_bus
            )

            setBackgroundTint(view, colorMap[busColor] ?: R.color.black_3)
        }

        fun setBackgroundTint(view: View, colorId: Int) {
            var drawable: Drawable? = view.background
            drawable = DrawableCompat.wrap(drawable!!)

            DrawableCompat.setTint(drawable, ContextCompat.getColor(view.context, colorId))
        }
    }
}