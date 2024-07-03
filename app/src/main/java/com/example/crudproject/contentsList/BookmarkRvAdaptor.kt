package com.example.crudproject.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crudproject.R
import com.example.crudproject.utils.FBAuth
import com.example.crudproject.utils.FBRef

class BookmarkRvAdaptor(
    val context : Context,
    val items : ArrayList<ContentModel>,
    val keyList : ArrayList<String>,
    val bookMarkIdList : MutableList<String>
) : RecyclerView.Adapter<BookmarkRvAdaptor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkRvAdaptor.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)


        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookmarkRvAdaptor.ViewHolder, position: Int) {
        holder.bindItem(items[position], keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item : ContentModel, key : String) {

            itemView.setOnClickListener {
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)

                itemView.context.startActivity(intent)
            }

            var contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            var imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            var bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if(bookMarkIdList.contains(key)) {
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            } else {
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }

            contentTitle.text = item.title

            // context =  activity에서 사용하는 전체 맥락 같은 개념
            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)

        }
    }

}