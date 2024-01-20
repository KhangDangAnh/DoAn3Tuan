package com.example.doan_3tuan.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.doan_3tuan.Video
import com.google.firebase.storage.FirebaseStorage

//class VideoViewModel : ViewModel() {
//    private val storage = FirebaseStorage.getInstance()
//    private val storageReference = storage.reference.child("videos")
//
//    private val _videos = mutableStateOf<List<Video>>(emptyList())
//    val videos: State<List<Video>> = _videos
//
//    init {
//        storageReference.listAll().addOnSuccessListener { result ->
//            val videoList = result.items.map { Video(it.downloadUrl.toString()) }
//            _videos.value = videoList
//        }.addOnFailureListener {
//            emptyList<Video>()
//        }
//    }
//}