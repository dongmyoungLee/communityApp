package com.example.crudproject.contentsList

import android.os.Bundle
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
        items.add(ContentModel("imageUrl1", "title1"))
        items.add(ContentModel("imageUrl2", "title2"))
        items.add(ContentModel("imageUrl3", "title3"))

        val rvAdaptor = ContentRVAdaptor(items)

        rv.adapter = rvAdaptor

        rv.layoutManager = GridLayoutManager(this ,2)

    }
}