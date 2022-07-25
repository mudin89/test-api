package com.mudin.test.myapplication.retrofit

import com.mudin.test.myapplication.model.Comment
import com.mudin.test.myapplication.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("posts")
    fun getServices() : Call<List<User>>

    @GET("posts/{post_id}/comments")
    fun getComment(@Path(value = "post_id", encoded = true) postId : String) : Call<List<Comment>>

}