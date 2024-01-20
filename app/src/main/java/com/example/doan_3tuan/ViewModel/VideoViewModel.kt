package com.example.doan_3tuan.ViewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.Model.VideoFirestore
import com.example.doan_3tuan.Video
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await



class VideoViewModel:ViewModel(){
    val state = mutableStateOf(VideoFirestore(id = 0, videoURL = "", userName = "", userImage = "", music = "", likesCount = 0, isLiked = false, commentsCount = 0, comment = ""))

    init {
        getVideo()
    }
    private fun getVideo(){
        viewModelScope.launch {
            state.value = getVideoFromFirestore()
        }
    }
}
suspend fun getVideoFromFirestore():VideoFirestore{
    val db = FirebaseFirestore.getInstance()
    var video = VideoFirestore(id = 0, videoURL = "", userName = "", userImage = "", music = "", likesCount = 0, isLiked = false, commentsCount = 0, comment = "")
    try {
        db.collection("videos").get().await().map {
            val result = it.toObject(VideoFirestore::class.java)
            video = result
        }
    }catch (e:FirebaseFirestoreException){
        Log.d("error","getVideoFromFirestore: $e")
    }
    return  video
}
//class VideoViewModel:ViewModel() {
//    var state by mutableStateOf(VideoState())
//        private set
//    init {
//        getAllContact()
//    }
//
//    fun getAllContact(){
//        viewModelScope.launch {
//            var ls = mutableListOf<VideoFirestore>()
//                Firebase.firestore.collection("videos")
//                    .addSnapshotListener { value, error ->
//                        if(value != null){
//                            for(doc in value){
//                                var video = doc.toObject(VideoFirestore::class.java)
//                                ls.add(video)
//                            }
//                        }
//                        state = state.copy(
//                            contactList = ls
//                        )
//                    }
//            }
//    }
//}
//
//data class VideoState(
//    val contactList: List<VideoFirestore> = emptyList()
//)