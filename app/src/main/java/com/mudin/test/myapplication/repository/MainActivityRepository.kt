package com.mudin.test.myapplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mudin.test.myapplication.di.AppXBus
import com.mudin.test.myapplication.model.Comment
import com.mudin.test.myapplication.model.CommentList
import com.mudin.test.myapplication.model.ServicesSetterGetter
import com.mudin.test.myapplication.model.User
import com.mudin.test.myapplication.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<ServicesSetterGetter>()
    val commentListService = MutableLiveData<CommentList>()

    fun getServicesApiCall(): MutableLiveData<ServicesSetterGetter> {

        val call = RetrofitClient.apiInterface.getServices()

        call.enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                AppXBus.publish(AppXBus.AppEvents.showError())
            }

            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data
                serviceSetterGetter.value = ServicesSetterGetter(msg)
            }
        })

        return serviceSetterGetter
    }

    fun getCommentApiCall(postId : String): MutableLiveData<CommentList> {

        val call = RetrofitClient.apiInterface.getComment(postId)

        call.enqueue(object: Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                AppXBus.publish(AppXBus.AppEvents.showError())
            }

            override fun onResponse(
                call: Call<List<Comment>>,
                response: Response<List<Comment>>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data
                commentListService.value = CommentList(msg)
            }
        })

        return commentListService
    }
}