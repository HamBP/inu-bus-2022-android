package org.algosketch.inubus.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import org.algosketch.inubus.R

class InformationAdapter(private val list: List<Int>) : RecyclerView.Adapter<InformationAdapter.InformationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_information, parent, false)

        return InformationViewHolder(view)
    }

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        holder.button.setOnClickListener {  }
    }

    override fun getItemCount(): Int = list.size

    inner class InformationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_detail)
    }
}