package com.mudin.test.myapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mudin.test.myapplication.R
import com.mudin.test.myapplication.databinding.ActivityCommentBinding
import com.mudin.test.myapplication.databinding.ActivityMainBinding
import com.mudin.test.myapplication.viewmodel.MainActivityViewModel

class CommentActivity : AppCompatActivity() {


        lateinit var context: Context

        lateinit var commentActivityModelView: MainActivityViewModel

        lateinit var binding : ActivityCommentBinding


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityCommentBinding.inflate(layoutInflater)
            setContentView(binding.root)

            commentActivityModelView = ViewModelProvider(this).get(MainActivityViewModel::class.java)

            commentActivityModelView.getUser()!!.observe(this, Observer { serviceSetterGetter ->

                setupComment()
                setupSearchBar()
            })
        }


    fun setupComment(){

    }

    fun setupSearchBar(){

    }
}
