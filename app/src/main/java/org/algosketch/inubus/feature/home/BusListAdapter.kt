package org.algosketch.inubus.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.algosketch.inubus.R
import org.algosketch.inubus.data.model.BusArrival

class BusListAdapter(val list: List<BusArrival>) : RecyclerView.Adapter<BusListAdapter.BusListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_information, parent, false)

        return BusListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusListViewHolder, position: Int) {
        holder.busNumber.text = "16"
        holder.estimatedTime.text = "15"
        holder.busArrivalTime.text = "8"
    }

    override fun getItemCount() = list.size

    inner class BusListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val busNumber = view.findViewById<TextView>(R.id.bus_number)
        val estimatedTime = view.findViewById<TextView>(R.id.estimated_time)
        val busArrivalTime = view.findViewById<TextView>(R.id.bus_arrival_time)
    }
}