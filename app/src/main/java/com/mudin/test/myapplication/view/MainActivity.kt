package com.mudin.test.myapplication.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mudin.test.myapplication.MyApplication
import com.mudin.test.myapplication.databinding.ActivityMainBinding
import com.mudin.test.myapplication.di.AppXBus
import com.mudin.test.myapplication.model.User
import com.mudin.test.myapplication.viewmodel.MainActivityViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    lateinit var context: Context

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    private lateinit var userRecyclerAdapter: UserRecyclerAdapter

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init of dagger injection
        (application as MyApplication).getAppComponent().doInjection(this)


        context = this@MainActivity
        initRecyclerView()

        binding.btnClick.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            mainActivityViewModel.getUser()!!.observe(this, Observer { serviceSetterGetter ->
                binding.progressBar.visibility = View.GONE

                val msg = serviceSetterGetter.userList
                msg?.let {
                    userRecyclerAdapter.submitList(msg)
                    userRecyclerAdapter.notifyDataSetChanged()
                }

            })
        }

        val errorSB = showErrorListener()
        compositeDisposable.add(errorSB)

    }


    private fun initRecyclerView() {
        binding.rcvResponse.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            userRecyclerAdapter = UserRecyclerAdapter()
            adapter = userRecyclerAdapter
        }
    }

    override fun onDestroy() { //clear any observable here to prevent crashed
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun showErrorListener(): Disposable {
        val d = AppXBus.listen(AppXBus.AppEvents.showError::class.java)
            .subscribe({ ok ->
                showError()
            }, { t: Throwable? ->  })
        return d
    }

    private fun showError(){
        Snackbar.make(binding.root, "Error in reaching the API", Snackbar.LENGTH_LONG).show()

    }


}
