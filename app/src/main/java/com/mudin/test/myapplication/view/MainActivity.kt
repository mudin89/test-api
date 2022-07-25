package com.mudin.test.myapplication.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mudin.test.myapplication.databinding.ActivityMainBinding
import com.mudin.test.myapplication.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {


    lateinit var context: Context
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var  binding : ActivityMainBinding
    private lateinit var userRecyclerAdapter: UserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this@MainActivity
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        initRecyclerView()

        binding.btnClick.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->
                binding.progressBar.visibility = View.GONE
                val msg = serviceSetterGetter.userList
                msg?.let{
                    userRecyclerAdapter.submitList(msg)
                    userRecyclerAdapter.notifyDataSetChanged()
                }
            })
        }
    }

    private fun initRecyclerView(){
        binding.rcvResponse.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            userRecyclerAdapter = UserRecyclerAdapter()
            adapter = userRecyclerAdapter
        }
    }


}
