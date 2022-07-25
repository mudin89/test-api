package com.mudin.test.myapplication.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mudin.test.myapplication.R
import com.mudin.test.myapplication.databinding.ActivityMainBinding
import com.mudin.test.myapplication.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    class MainActivity : AppCompatActivity() {

        lateinit var context: Context

        lateinit var mainActivityViewModel: MainActivityViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            context = this@MainActivity

            mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

            binding.btnClick.setOnClickListener {

                binding.progressBar.visibility = View.VISIBLE

                mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->

                    binding.progressBar.visibility = View.GONE

                    val msg = serviceSetterGetter.userList

                    binding.lblResponse.text = msg.toString()

                })

            }

        }
    }
}
