package org.algosketch.inubus.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.algosketch.inubus.R
import org.algosketch.inubus.data.model.BusInformation
import androidx.core.graphics.drawable.DrawableCompat

import android.graphics.drawable.Drawable
import androidx.core.os.bundleOf
import org.algosketch.inubus.global.constant.Bus
import org.algosketch.inubus.global.store.Store


class BusListAdapter(val list: List<BusInformation>) : RecyclerView.Adapter<BusListAdapter.BusListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_information, parent, false)

        return BusListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusListViewHolder, position: Int) {
        setBusNumber(holder.busNumber, position)

        holder.exit.text = "${Store.where.value!!}역 ${list[position].exit}번 출구"
        holder.busArrivalTime.text = "버스가 ${list[position].restTime}분 뒤 도착해요."
        holder.view.setOnClickListener {
            val navController = holder.view.findNavController()
            val bundle = bundleOf(
                "exit" to list[position].exit,
                "where" to Store.where.value!!,
                "busNumber" to list[position].busNumber,
                "distance" to Bus.getDistance(Store.where.value!!, list[position].busNumber)
            )
            navController.navigate(R.id.action_wrap_to_detail, bundle)
        }
    }

    override fun getItemCount() = list.size

    inner class BusListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val busNumber = view.findViewById<TextView>(R.id.bus_number)
        val exit = view.findViewById<TextView>(R.id.exit)
        val busArrivalTime = view.findViewById<TextView>(R.id.bus_arrival_time)
    }

    fun setBusNumber(busNumber: TextView, position: Int) {
        busNumber.text = "${list[position].busNumber}"

        var buttonDrawable: Drawable? = busNumber.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)
        when (list[position].busColor) {
            "red" -> DrawableCompat.setTint(buttonDrawable, ContextCompat.getColor(busNumber.context, R.color.red_bus))
            "blue" -> DrawableCompat.setTint(buttonDrawable, ContextCompat.getColor(busNumber.context, R.color.blue_bus))
            "green" -> DrawableCompat.setTint(buttonDrawable, ContextCompat.getColor(busNumber.context, R.color.green_bus))
            "purple" -> DrawableCompat.setTint(buttonDrawable, ContextCompat.getColor(busNumber.context, R.color.purple_bus))
            else -> DrawableCompat.setTint(buttonDrawable, ContextCompat.getColor(busNumber.context, R.color.black_3))
        }
    }
}