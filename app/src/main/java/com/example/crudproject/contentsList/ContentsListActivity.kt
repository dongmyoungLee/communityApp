package com.example.crudproject.contentsList

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudproject.R
import com.example.crudproject.utils.FBAuth
import com.example.crudproject.utils.FBRef
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ContentsListActivity : AppCompatActivity() {

    lateinit var myRef : DatabaseReference

    val bookMarkIdList = mutableListOf<String>()

    lateinit var rvAdaptor : ContentRVAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()

        rvAdaptor = ContentRVAdaptor(baseContext, items, itemKeyList, bookMarkIdList)

        // Write a message to the database
        val database = Firebase.database

        val category = intent.getStringExtra("category")

        if (category == "category1") {
            myRef = database.getReference("contents")

        } else if (category == "category2") {
            myRef = database.getReference("contents2")
        }

        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }

                // data 다 받아온 이후 adaptor refresh sync..
                rvAdaptor.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }

        myRef.addValueEventListener(postListener)

        val rv : RecyclerView = findViewById(R.id.rv)

        rv.adapter = rvAdaptor

        rv.layoutManager = GridLayoutManager(this ,2)

        getBookmark()
    }

    private fun getBookmark() {
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                bookMarkIdList.clear()

                for (dataModel in dataSnapshot.children) {
                    bookMarkIdList.add(dataModel.key.toString())
                }

                Log.d("ContentsListActivity", bookMarkIdList.toString())

                // 동기화
                rvAdaptor.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)
    }
}

