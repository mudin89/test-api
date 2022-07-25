package com.mudin.test.myapplication.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mudin.test.myapplication.MyApplication
import com.mudin.test.myapplication.databinding.ActivityCommentBinding
import com.mudin.test.myapplication.di.AppXBus
import com.mudin.test.myapplication.model.Comment
import com.mudin.test.myapplication.viewmodel.MainActivityViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class CommentActivity : AppCompatActivity() {


    lateinit var context: Context

    @Inject
    lateinit var commentActivityModelView: MainActivityViewModel
    lateinit var binding: ActivityCommentBinding
    private lateinit var commentRecyclerAdapter: CommentRecyclerAdapter
    var currentComment  = mutableListOf<Comment>()

    @Inject
    lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init of dagger injection
        (application as MyApplication).getAppComponent().doInjection(this)


        initRecyclerView()

        var postId : String = "0"

        if(intent.getStringExtra("POST_ID") != null) {
            postId = intent.getStringExtra("POST_ID").toString()
        }

        commentActivityModelView.getComment(postId)!!.observe(this, Observer { serviceSetterGetter ->

            val msg = serviceSetterGetter.commentList
            msg?.let{
                commentRecyclerAdapter.submitList(msg)
                commentRecyclerAdapter.notifyDataSetChanged()

                currentComment.addAll(msg)
            }

            binding.searchView.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    filter(p0.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })
        })

        val errorSB = showErrorListener()
        compositeDisposable.add(errorSB)


    }


    private fun initRecyclerView() {
        binding.rcvComment.apply {
            layoutManager = LinearLayoutManager(this@CommentActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            commentRecyclerAdapter = CommentRecyclerAdapter()
            adapter = commentRecyclerAdapter
        }
    }

    fun filter(text: String) {
        val filteredCourseAry: ArrayList<Comment> = ArrayList()
        for (eachComment in currentComment) {
            if (eachComment!!.body.toLowerCase().contains(text.toLowerCase())) {
                filteredCourseAry.add(eachComment)
            }
        }
        //calling a method of the adapter class and passing the filtered list
        commentRecyclerAdapter.submitList(filteredCourseAry)
        commentRecyclerAdapter.notifyDataSetChanged()
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
