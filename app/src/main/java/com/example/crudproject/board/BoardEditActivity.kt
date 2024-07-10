package com.example.crudproject.board

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.crudproject.R
import com.example.crudproject.databinding.ActivityBoardEditBinding
import com.example.crudproject.utils.FBAuth
import com.example.crudproject.utils.FBRef
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.storage

class BoardEditActivity : AppCompatActivity() {

    private lateinit var key:String

    private lateinit var binding : ActivityBoardEditBinding

    private val TAG = BoardEditActivity::class.java.simpleName

    private lateinit var writerUid : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_edit)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_edit)

        binding.editBtn.setOnClickListener {
            editBoardData(key)
        }

        // 두번째 방법
        key = intent.getStringExtra("key").toString()

        getBoardData(key)
        getImageData(key)
    }

    private fun editBoardData(key: String) {

        FBRef.boardRef
            .child(key)
            .setValue(BoardModel(binding.titleArea.text.toString(), binding.contentArea.text.toString(), FBAuth.getUid(), FBAuth.getTime()))

        Toast.makeText(this, "수정완료", Toast.LENGTH_LONG).show()

        finish()

    }

    private fun getImageData(key : String) {

        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference.child(key + ".png")

        // ImageView in your Activity
        val imageViewFromFB = binding.imageArea

        storageReference.downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide.with(this)
                    .load(task.result)
                    .into(imageViewFromFB)
            } else {

            }
        }
    }

    private fun getBoardData(key : String) {
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {


                val dataModel = dataSnapshot.getValue(BoardModel::class.java)

                binding.titleArea.setText(dataModel?.title)
                binding.contentArea.setText(dataModel?.content)
                writerUid = dataModel!!.uid

            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.boardRef.child(key).addValueEventListener(postListener)
    }
}