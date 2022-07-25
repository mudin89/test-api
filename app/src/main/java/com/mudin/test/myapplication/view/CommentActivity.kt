package com.mudin.test.myapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mudin.test.myapplication.R
import com.mudin.test.myapplication.databinding.ActivityCommentBinding
import com.mudin.test.myapplication.databinding.ActivityMainBinding
import com.mudin.test.myapplication.model.CommentList
import com.mudin.test.myapplication.viewmodel.MainActivityViewModel

class CommentActivity : AppCompatActivity() {


    lateinit var context: Context
    lateinit var commentActivityModelView: MainActivityViewModel
    lateinit var binding: ActivityCommentBinding
    private lateinit var CommentRecyclerAdapter: CommentRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commentActivityModelView = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        initRecyclerView()

        commentActivityModelView.getComment("1")!!.observe(this, Observer { serviceSetterGetter ->

            val msg = serviceSetterGetter.commentList
            msg?.let{
                CommentRecyclerAdapter.submitList(msg)
                CommentRecyclerAdapter.notifyDataSetChanged()
            }
        })
    }


    private fun initRecyclerView() {
        binding.rcvComment.apply {
            layoutManager = LinearLayoutManager(this@CommentActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            CommentRecyclerAdapter = CommentRecyclerAdapter()
            adapter = CommentRecyclerAdapter
        }
    }


}
