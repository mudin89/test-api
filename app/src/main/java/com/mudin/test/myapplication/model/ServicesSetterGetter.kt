package com.mudin.test.myapplication.model

data class ServicesSetterGetter (
    val userList: List<User>? = null
)

data class CommentList (
    val commentList: List<Comment>? = null
)

data class User(val userId : Int, val id : Int, val title : String, val body : String)


data class Comment(val postId : Int, val id : Int, val name : String, val email : String, val body : String)