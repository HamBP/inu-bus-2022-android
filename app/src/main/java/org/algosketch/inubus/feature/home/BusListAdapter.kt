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
import android.os.Bundle
import androidx.core.os.bundleOf
import org.algosketch.inubus.global.util.Bus
import org.algosketch.inubus.global.store.Store


class BusListAdapter(val list: List<BusInformation>) : RecyclerView.Adapter<BusListAdapter.BusListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_information, parent, false)

        return BusListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusListViewHolder, position: Int) {
        val where = Store.where.value!!
        val exit = list[position].exit
        val restTime = list[position].restTime
        val busNumber = list[position].busNumber
        val distance = Bus.getDistance(where, busNumber)

        setBusNumber(holder.busNumber, busNumber)

        holder.exit.text = "${where}역 ${exit}번 출구"
        holder.busArrivalTime.text = "버스가 ${restTime}분 뒤 도착해요."
        holder.view.setOnClickListener {
            val navController = holder.view.findNavController()
            val bundle = getBundle(exit, where, busNumber, distance, restTime)
            navController.navigate(R.id.action_wrap_to_detail, bundle)
        }
        holder.tagRecyclerView.adapter = TagAdapter(Bus.getBusStopsByBusNumber(busNumber))
    }

    fun getBundle(exit: Int, where: String, busNumber: String, distance: Int, restTime: Int) : Bundle {
        val bundle = bundleOf(
            "exit" to exit,
            "where" to where,
            "busNumber" to busNumber,
            "distance" to distance,
            "restTime" to restTime
        )

        return bundle
    }

    override fun getItemCount() = list.size

    inner class BusListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val busNumber: TextView = view.findViewById(R.id.bus_number)
        val exit: TextView = view.findViewById(R.id.exit)
        val busArrivalTime: TextView = view.findViewById(R.id.bus_arrival_time)
        val tagRecyclerView: RecyclerView = view.findViewWithTag(R.id.tag_recycler_view)
    }

    fun setBusNumber(view: TextView, busNumber: String) {
        view.text = busNumber

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
        var buttonDrawable: Drawable? = view.background
        buttonDrawable = DrawableCompat.wrap(buttonDrawable!!)

        DrawableCompat.setTint(buttonDrawable, ContextCompat.getColor(view.context, colorId))
    }
}