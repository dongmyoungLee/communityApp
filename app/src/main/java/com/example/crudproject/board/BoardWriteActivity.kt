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
import com.example.crudproject.contentsList.BookmarkModel
import com.example.crudproject.databinding.ActivityBoardWriteBinding
import com.example.crudproject.databinding.ActivityIntroBinding
import com.example.crudproject.utils.FBAuth
import com.example.crudproject.utils.FBRef
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BoardWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardWriteBinding
    private val TAG = BoardWriteActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_write)

        binding.writeBtn.setOnClickListener {

            val title = binding.titleArea.text.toString()
            val content = binding.contentArea.text.toString()
            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()


            Log.d(TAG, title)
            Log.d(TAG, content)

            FBRef.boardRef
                .push()
                .setValue(BoardModel(title, content, uid, time))

            Toast.makeText(this, "게시글 입력 완료", Toast.LENGTH_LONG).show()

            finish()

        }


    }
}