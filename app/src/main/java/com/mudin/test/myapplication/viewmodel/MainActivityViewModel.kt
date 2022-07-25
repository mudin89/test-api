package com.mudin.test.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mudin.test.myapplication.model.Comment
import com.mudin.test.myapplication.model.CommentList
import com.mudin.test.myapplication.model.ServicesSetterGetter
import com.mudin.test.myapplication.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<ServicesSetterGetter>? = null
    var commentList: MutableLiveData<CommentList>? = null

    fun getUser() : LiveData<ServicesSetterGetter>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall()
        return servicesLiveData
    }

    fun getComment(postId : String) : LiveData<CommentList>? {
        commentList = MainActivityRepository.getCommentApiCall(postId)
        return commentList
    }

}