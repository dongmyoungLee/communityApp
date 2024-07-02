package com.example.crudproject.contentsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crudproject.R

class ContentRVAdaptor(val context : Context, val items : ArrayList<ContentModel>) : RecyclerView.Adapter<ContentRVAdaptor.ViewHolder>() {

    // contents click
    interface ItemClick {
        fun onClick(view : View , position: Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdaptor.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdaptor.ViewHolder, position: Int) {

        if(itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }

        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item : ContentModel) {

            var contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            var imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)

            contentTitle.text = item.title

            // context =  activity에서 사용하는 전체 맥락 같은 개념
            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

        }
    }

}