package com.example.doan_3tuan.Model

data class VideoFirestore (
    val id: Int,
    val videoURL: String="",
    val userImage: String="",
    val userName: String="",
    val isLiked: Boolean = false,
    val likesCount: Int,
    val comment: String="",
    val commentsCount: Int,
    val music:String=""
)