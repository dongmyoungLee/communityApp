package com.example.crudproject.utils

import com.google.firebase.Firebase
import com.google.firebase.database.database

class FBRef {
    companion object {
        // Write a message to the database
        private val database = Firebase.database

        val category1 = database.getReference("contents")
        val category2 = database.getReference("contents2")

        val bookmarkRef =  database.getReference("bookmark_list")
        val boardRef =  database.getReference("board")
        val commentRef =  database.getReference("comment")
    }
}