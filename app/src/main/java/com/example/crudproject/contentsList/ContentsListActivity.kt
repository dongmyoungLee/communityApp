package com.example.crudproject.contentsList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudproject.R

class ContentsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val rv : RecyclerView = findViewById(R.id.rv)

        val items = ArrayList<ContentModel>()
        items.add(ContentModel("title1", "https://cdn.oasis.co.kr:48581/product/4778/thumb/thumb_4778799eda1c-6f74-49b9-b435-b33c6ad83377.gif", "https://www.daum.net"))
        items.add(ContentModel("title2","https://www.fetv.co.kr/data/photos/20190519/art_1557445612064_466d7a.jpg", "https://www.daum.net"))
        items.add(ContentModel("title3", "https://static.megamart.com/product/image/1038/10384206/10384206_1_960.jpg", "https://www.daum.net"))

        val rvAdaptor = ContentRVAdaptor(baseContext, items)

        rv.adapter = rvAdaptor

        rv.layoutManager = GridLayoutManager(this ,2)

        rvAdaptor.itemClick = object : ContentRVAdaptor.ItemClick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(baseContext, items[position].title, Toast.LENGTH_LONG).show()

                // webview
                val intent = Intent(this@ContentsListActivity, ContentShowActivity::class.java)
                intent.putExtra("url", items[position].webUrl)
                startActivity(intent)
            }

        }
    }
}