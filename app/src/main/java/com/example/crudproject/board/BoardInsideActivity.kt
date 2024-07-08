package com.example.crudproject.board

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.crudproject.R
import com.example.crudproject.databinding.ActivityBoardInsideBinding
import com.example.crudproject.databinding.ActivityBoardWriteBinding
import com.example.crudproject.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class BoardInsideActivity : AppCompatActivity() {

    private val TAG = BoardInsideActivity::class.java.simpleName

    private lateinit var binding: ActivityBoardInsideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_inside)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_inside)
//            첫번째 방법
//        val title = intent.getStringExtra("title").toString()
//        val content = intent.getStringExtra("content")
//        val time = intent.getStringExtra("time")
//
//        binding.titleArea.text = title
//        binding.contentArea.text = content
//        binding.timeArea.text = time

        // 두번째 방법
        val key = intent.getStringExtra("key")
        getBoardData(key.toString())

    }

    private fun getBoardData(key : String) {
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                Log.d(TAG, dataSnapshot.toString())
                val dataModel = dataSnapshot.getValue(BoardModel::class.java)

                binding.titleArea.text = dataModel!!.title
                binding.contentArea.text = dataModel!!.content
                binding.timeArea.text = dataModel!!.time
            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}