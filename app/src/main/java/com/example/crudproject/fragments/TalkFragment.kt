package com.example.crudproject.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.crudproject.R
import com.example.crudproject.board.BoardInsideActivity
import com.example.crudproject.board.BoardListLVAdaptor
import com.example.crudproject.board.BoardModel
import com.example.crudproject.board.BoardWriteActivity
import com.example.crudproject.contentsList.ContentModel
import com.example.crudproject.databinding.FragmentTalkBinding
import com.example.crudproject.databinding.FragmentTipBinding
import com.example.crudproject.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TalkFragment : Fragment() {

    private lateinit var binding : FragmentTalkBinding

    private val boardDataList = mutableListOf<BoardModel>()

    private val boardKeyList =  mutableListOf<String>()

    private val TAG = TalkFragment::class.java.simpleName

    private lateinit var boardRVadaptor : BoardListLVAdaptor




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        boardRVadaptor  = BoardListLVAdaptor(boardDataList)
        binding.boardListView.adapter = boardRVadaptor

        // 게시글 상세 만들기

        // 첫번째 방법 - listView에 있는 데이터 title, content, time 다 다른 액티비티로 전달해줘서 만들기
//        binding.boardListView.setOnItemClickListener { parent, view, position, id ->
//            val intent = Intent(context, BoardInsideActivity::class.java)
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
//            startActivity(intent)
//        }

        // 두번째 방법 - Firebase에 있는 board에 대한 데이터의 id를 기반으로 다시 데이터를 불러오는 방법
        binding.boardListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)
        }


        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }

        binding.tipTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }

        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_bookmarkFragment)
        }

        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }

        getFBBoardData()

        return binding.root
    }

    private fun getFBBoardData() {
        val postListener = object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()

                for (dataModel in dataSnapshot.children) {
                    val item = dataModel.getValue(BoardModel::class.java)

                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())
                }
                boardKeyList.reverse()
                boardDataList.reverse()
                boardRVadaptor.notifyDataSetChanged()
            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        FBRef.boardRef.addValueEventListener(postListener)
    }
}