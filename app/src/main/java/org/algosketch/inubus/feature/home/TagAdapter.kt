package org.algosketch.inubus.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.algosketch.inubus.R

class TagAdapter(val tags: List<String>) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false)

        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.tagTextView.text = tags[position]
    }

    override fun getItemCount() = tags.size

    inner class TagViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tagTextView = view.findViewById<TextView>(R.id.item_tag)
    }
}