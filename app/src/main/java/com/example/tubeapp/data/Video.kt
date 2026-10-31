package com.example.tubeapp.data

data class Comment(val author: String, val text: String)

data class Video(
    val id: Int,
    val title: String,
    val channel: String,
    val views: String,
    val age: String,
    val duration: String,
    val thumbnail: String,
    val url: String,
    val description: String,
    val likes: Int,
    val comments: List<Comment>
)
