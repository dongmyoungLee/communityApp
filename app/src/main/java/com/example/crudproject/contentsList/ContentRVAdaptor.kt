package com.example.crudproject.contentsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudproject.R

class ContentRVAdaptor(val items : ArrayList<ContentModel>) : RecyclerView.Adapter<ContentRVAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdaptor.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdaptor.ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item : ContentModel) {

            var contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            contentTitle.text = item.title

        }
    }

}